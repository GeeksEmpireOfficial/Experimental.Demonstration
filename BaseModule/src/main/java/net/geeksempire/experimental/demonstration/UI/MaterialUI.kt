package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.drawable.Animatable
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        ((findViewById(R.id.vectorControl) as AppCompatImageView).drawable as Animatable).start()
        val animatable = (findViewById(R.id.vectorControl) as AppCompatImageView).drawable as Animatable

        animatable.start()
    }
}