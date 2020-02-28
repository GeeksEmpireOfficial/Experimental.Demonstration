package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        val animatedVectorDrawable: AnimatedVectorDrawable = vectorControl.drawable as AnimatedVectorDrawable
        animatedVectorDrawable.start()

        animatedVectorDrawable?.registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                animatedVectorDrawable.start()
            }
        })
    }
}