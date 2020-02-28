package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        horizontalProgressView.progress = 77f
        circleProgressView.progress = 55f


    }
}