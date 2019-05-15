package com.geekstest.dynamicfeaturedemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.Task
import kotlinx.android.synthetic.main.app_main_view.*


class AppViewActivity : BaseConfigurations() {

    lateinit var splitInstallManager: SplitInstallManager
    lateinit var splitInstallStateUpdatedListener: SplitInstallStateUpdatedListener

    lateinit var appUpdateManager: AppUpdateManager
    lateinit var installStateUpdatedListener: InstallStateUpdatedListener

    val SPLIT_REQUEST_CODE = 111
    val IN_APP_UPDATE_REQUEST = 333

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_main_view)

        splitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
            when (splitInstallSessionState.status()) {
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    //Play Store.
                    println("*** Split Confirmation ***")
                    splitInstallManager.startConfirmationDialogForResult(
                        splitInstallSessionState,
                        this@AppViewActivity,
                        SPLIT_REQUEST_CODE
                    )
                }
                SplitInstallSessionStatus.DOWNLOADING -> {
                    //Play Store.
                    println("*** Split Downloading ***")
                }
                SplitInstallSessionStatus.DOWNLOADED -> {

                }
                SplitInstallSessionStatus.INSTALLING -> {
                    println("*** Split Installing ***")
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    println("*** Module Installed: ${splitInstallSessionState.moduleNames()[0]}")
                    Handler().run {
                        SplitInstallHelper.updateAppInfo(applicationContext)

//                        val dynamicFunctionsClass = DynamicFunctionsClass(applicationContext)
                    }
                    when (splitInstallSessionState.moduleNames()[0]) {
                        BaseConfigurations.dynamicModule -> {
                            Intent().setClassName(BuildConfig.APPLICATION_ID, BaseConfigurations.dynamicClassName)
                                .also {
                                    it.putExtra("DATA_TO_DYNAMIC", "AFTER INSTALLED")
                                    startActivity(it)
                                }
                        }
                    }
                    splitInstallManager.unregisterListener(splitInstallStateUpdatedListener)
                }
                SplitInstallSessionStatus.FAILED -> {

                }
            }
        }
        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        splitInstallManager.registerListener(splitInstallStateUpdatedListener)
        val installedModule = splitInstallManager.installedModules
        installedModule.forEach {
            println("*** Installed Modules: " + it + " ***")
        }

        installStateUpdatedListener = InstallStateUpdatedListener {
            when (it.installStatus()) {
                InstallStatus.REQUIRES_UI_INTENT -> {
                    println("*** UPDATE UI Intent ***")
                }
                InstallStatus.DOWNLOADING -> {
                    println("*** UPDATE Downloading ***")
                }
                InstallStatus.DOWNLOADED -> {
                    println("*** UPDATE Downloaded ***")

                    showCompleteConfirmation()
                }
                InstallStatus.INSTALLING -> {
                    println("*** UPDATE Installing ***")
                }
                InstallStatus.INSTALLED -> {
                    println("*** UPDATE Installed ***")

                    appUpdateManager.unregisterListener(installStateUpdatedListener)
                }
                InstallStatus.CANCELED -> {
                    println("*** UPDATE Canceled ***")
                }
                InstallStatus.FAILED -> {
                    println("*** UPDATE Failed ***")
                }
            }
        }
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        appUpdateManager.registerListener(installStateUpdatedListener)
        val appUpdateInfo: Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
        appUpdateInfo
            .addOnCompleteListener {
                val updateInfo = it.result
            }
            .addOnSuccessListener { updateInfo ->

                println("*** ${updateInfo.updateAvailability()} --- ${updateInfo.availableVersionCode()} ***")
                if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && updateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        updateInfo,
                        AppUpdateType.IMMEDIATE,
                        this@AppViewActivity,
                        IN_APP_UPDATE_REQUEST
                    )
                }

            }
            .addOnFailureListener {
                println("*** Exception Error ${it} ***")
            }

        dynamicFeature.setOnClickListener {
            if (installedModule.contains(BaseConfigurations.dynamicModule)) {
                Intent().setClassName(BuildConfig.APPLICATION_ID, BaseConfigurations.dynamicClassName).also {
                    it.putExtra("DATA_TO_DYNAMIC", "ALREADY INSTALLED")
                    startActivity(it)
                }
            } else {
                val splitInstallRequest: SplitInstallRequest =
                    SplitInstallRequest
                        .newBuilder()
                        .addModule(BaseConfigurations.dynamicModule)
                        .build()

                splitInstallManager
                    .startInstall(splitInstallRequest)
                    .addOnSuccessListener {
                        println("Module Installed Successfully")
                    }
                    .addOnFailureListener {
                        println("Exception Error:" + it)
                    }
            }
        }
        dynamicFeature.setOnLongClickListener {

            val moduleToUninstall = listOf<String>(
                BaseConfigurations.dynamicModule
            )
            splitInstallManager.deferredUninstall(moduleToUninstall).addOnSuccessListener {
                println("Module Uninstalled Successfully")

                splitInstallManager.installedModules.forEach {
                    println("*** Installed Modules After Uninstallation: " + it + " ***")
                }
            }.addOnFailureListener {
                println("Exception Error:" + it)
            }

            return@setOnLongClickListener true
        }
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this@AppViewActivity,
                        IN_APP_UPDATE_REQUEST
                    )
                }

                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    showCompleteConfirmation()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPLIT_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                println("*** Split Downloading Canceled ***")
            } else if (resultCode == RESULT_OK) {
                println("*** Split Downloaded Successfully ***")
            }
        } else if (requestCode == IN_APP_UPDATE_REQUEST) {
            if (resultCode == RESULT_CANCELED) {


            } else if (resultCode == RESULT_OK) {

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun showCompleteConfirmation() {
        val snackbar = Snackbar.make(
            findViewById<ConstraintLayout>(R.id.mainView),
            "An Update has Just Been Installed.",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction("Complete It!") { view -> appUpdateManager.completeUpdate() }
        snackbar.setActionTextColor(getColor(android.R.color.holo_blue_bright))
        snackbar.show()
    }
}