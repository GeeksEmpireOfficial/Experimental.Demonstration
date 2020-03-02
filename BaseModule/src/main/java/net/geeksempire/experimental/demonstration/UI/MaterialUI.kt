package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        val animatable = getDrawable(R.drawable.animated_geeksempire) as Animatable
        animatable.start()

        vectorControlOne.setImageDrawable(animatable as Drawable)
    }
}