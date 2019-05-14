package com.geekstest.dynamicfeaturedemo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.android.play.core.splitcompat.SplitCompat

open class BaseConfigurations : Activity() {

    companion object {
        const val dynamicModule = "DynamicFeatureOnDemand"
        const val dynamicPackageName = "com.geekstest.dynamicfeatureondemand"
        const val dynamicClassName = "$dynamicPackageName.DynamicActivity"
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
