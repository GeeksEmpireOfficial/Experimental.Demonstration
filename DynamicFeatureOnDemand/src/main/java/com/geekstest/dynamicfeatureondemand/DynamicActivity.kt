package com.geekstest.dynamicfeatureondemand

import android.os.Bundle
import android.widget.Toast
import com.geekstest.dynamicfeaturedemo.BaseConfigurations

class DynamicActivity : BaseConfigurations() {

    companion object {
        const val xNumber: Int = 333
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        Toast.makeText(applicationContext, intent.getStringExtra("DATA_TO_DYNAMIC"), Toast.LENGTH_LONG).show()
    }
}
