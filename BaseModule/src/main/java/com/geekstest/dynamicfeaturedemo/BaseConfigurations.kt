package com.geekstest.dynamicfeaturedemo

import android.app.Activity
import android.os.Bundle
import com.google.android.play.core.splitcompat.SplitCompat

open class BaseConfigurations : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplitCompat.install(this)
    }
}
