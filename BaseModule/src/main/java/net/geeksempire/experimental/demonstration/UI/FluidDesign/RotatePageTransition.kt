package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class RotatePageTransition : ViewPager2.PageTransformer {

    val pageRotation = -15f

    override fun transformPage(page: View, position: Float) {

        val width = page.width
        val height = page.height

        val rotation = pageRotation * position * -1.25f;

        page.pivotX = width * 0.5f
        page.pivotY = height.toFloat()
        page.rotation = rotation

    }
}