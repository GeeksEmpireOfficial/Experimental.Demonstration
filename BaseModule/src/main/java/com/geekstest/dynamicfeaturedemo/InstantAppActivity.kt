package com.geekstest.dynamicfeaturedemo

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.instant_view.*


class InstantAppActivity : BaseConfigurations() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instant_view)

        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data

        welcome.setOnClickListener {
            Toast.makeText(applicationContext, getString(R.string.instantAppWelcome), Toast.LENGTH_LONG).show()
        }
    }
}