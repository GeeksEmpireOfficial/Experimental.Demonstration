package com.geekstest.dynamicfeaturedemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.Task
import kotlinx.android.synthetic.main.app_view.*


class AppViewActivity : BaseConfigurations() {

    lateinit var splitInstallManager: SplitInstallManager
    lateinit var splitInstallStateUpdatedListener: SplitInstallStateUpdatedListener

    lateinit var appUpdateManager: AppUpdateManager
    lateinit var installStateUpdatedListener: InstallStateUpdatedListener

    val SPLIT_REQUEST_CODE = 111
    val IN_APP_UPDATE_REQUEST = 333

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_view)

        splitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
            when (splitInstallSessionState.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    //Play Store.
                    println("*** Split Downloading ***")
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    //Play Store.
                    println("*** Split Confirmation ***")
                    splitInstallManager.startConfirmationDialogForResult(
                        splitInstallSessionState,
                        this@AppViewActivity,
                        SPLIT_REQUEST_CODE
                    )
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
                }

                SplitInstallSessionStatus.INSTALLING -> {
                    println("*** Split Installing ***")
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

            }
        }
        // Creates instance of the manager.
        appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        appUpdateManager.registerListener(installStateUpdatedListener)
        // Returns an intent object that you use to check for an update.
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

        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        IN_APP_UPDATE_REQUEST
                    )
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
}