package com.geekstest.dynamicfeaturedemo

import android.content.Intent
import android.os.Bundle
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.android.synthetic.main.app_view.*

class AppViewActivity : BaseConfigurations() {

    lateinit var splitInstallManager: SplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_view)

        val splitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
            when (splitInstallSessionState.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    //Play Store.

                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    //Play Store.
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    println("*** Module Installed: ${splitInstallSessionState.moduleNames()[0]}")
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

                }
                SplitInstallSessionStatus.FAILED -> {

                }
            }
        }

        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)
        splitInstallManager
            .registerListener(splitInstallStateUpdatedListener)
        val installedModule = splitInstallManager.installedModules
        installedModule.forEach {
            println("*** Installed Modules " + it + " ***")
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
                        println("Module Installed Successfully: " + it)
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
                println("Module Uninstalled Successfully: ")

                splitInstallManager.installedModules.forEach {
                    println("*** Installed Modules " + it + " ***")
                }
            }.addOnFailureListener {
                println("Exception Error:" + it)
            }

            return@setOnLongClickListener true
        }
    }
}