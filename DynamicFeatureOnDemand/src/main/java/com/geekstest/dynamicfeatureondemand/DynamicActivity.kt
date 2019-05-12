package com.geekstest.dynamicfeatureondemand

import android.os.Bundle
import com.geekstest.dynamicfeaturedemo.BaseConfigurations

class DynamicActivity : BaseConfigurations() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
    }
}
