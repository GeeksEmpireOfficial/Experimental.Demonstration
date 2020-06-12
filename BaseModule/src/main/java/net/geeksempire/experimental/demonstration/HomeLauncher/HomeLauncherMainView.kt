package net.geeksempire.experimental.demonstration.HomeLauncher

import android.content.Context
import android.content.pm.LauncherApps
import android.content.pm.LauncherApps.ShortcutQuery
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.UserHandle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.HomeLauncherViewBinding


class HomeLauncherMainView : AppCompatActivity() {

    lateinit var homeLauncherViewBinding: HomeLauncherViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeLauncherViewBinding = HomeLauncherViewBinding.inflate(layoutInflater)
        setContentView(homeLauncherViewBinding.root)

        val targetPackageName = "com.google.android.youtube"

        homeLauncherViewBinding.getShortcutsInformation.text = packageManager.getApplicationLabel(packageManager.getApplicationInfo(targetPackageName, PackageManager.GET_META_DATA))

        val launcherApps = getSystemService(Context.LAUNCHER_APPS_SERVICE) as LauncherApps

        homeLauncherViewBinding.getShortcutsInformation.setOnClickListener {

            if (launcherApps.hasShortcutHostPermission()) {

                val queryFlags = (
                        ShortcutQuery.FLAG_MATCH_DYNAMIC
                                or ShortcutQuery.FLAG_MATCH_MANIFEST
                                or ShortcutQuery.FLAG_MATCH_PINNED
                        )

                val applicationInfo = packageManager.getApplicationInfo(targetPackageName, 0)

                val shortcutInfoList =
                    launcherApps.getShortcuts(
                        ShortcutQuery().setPackage(targetPackageName).setQueryFlags(queryFlags),
                        UserHandle.getUserHandleForUid(applicationInfo.uid)
                    )

                shortcutInfoList?.forEach { shortcutInfo ->
                    println("AppShortcut :: ${shortcutInfo.id} - ${shortcutInfo.shortLabel} - ${shortcutInfo.activity} -")

                }

            }

        }

        homeLauncherViewBinding.getShortcutsInformation.setOnLongClickListener { view ->

            if (launcherApps.hasShortcutHostPermission()) {

                val queryFlags = (
                        ShortcutQuery.FLAG_MATCH_DYNAMIC
                                or ShortcutQuery.FLAG_MATCH_MANIFEST
                                or ShortcutQuery.FLAG_MATCH_PINNED
                        )

                val applicationInfo = packageManager.getApplicationInfo(targetPackageName, 0)

                val shortcutInfoList =
                    launcherApps.getShortcuts(
                        ShortcutQuery().setPackage(targetPackageName).setQueryFlags(queryFlags),
                        UserHandle.getUserHandleForUid(applicationInfo.uid)
                    )

                shortcutInfoList?.let {

                    val shortcutInformationToLauncher = it[1]

                    //new-tab-shortcut - New tab - ComponentInfo{com.android.chrome/com.google.android.apps.chrome.Main}
                    //dynamic-new-incognito-tab-shortcut - Incognito Tab - ComponentInfo{com.android.chrome/com.google.android.apps.chrome.Main}


                   // val componentInfo = ComponentInfo("com.android.chrome", "com.google.android.apps.chrome.Main")


//                    launcherApps.startShortcut(
//                        /*shortcutInformationToLauncher.`package`*/targetPackageName,
//                        /*shortcutInformationToLauncher.id*/"dynamic-new-incognito-tab-shortcut",
//                        view.clipBounds,
//                        ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle(),
//                        UserHandle.getUserHandleForUid(applicationInfo.uid)
//                    )
//                    launcherApps.startShortcut(
//                        shortcutInformationToLauncher,
//                        view.clipBounds,
//                        ActivityOptions.makeCustomAnimation(applicationContext, android.R.anim.fade_in, android.R.anim.fade_out).toBundle()
//                    )

                }

            }

            true
        }

    }
}