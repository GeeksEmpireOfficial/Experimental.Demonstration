package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        val animatable = getDrawable(R.drawable.animated_geeksempire) as Animatable
        animatable.start()

        vectorControlOne.setImageDrawable(animatable as Drawable)

        setupNegativeSpaceShape()

    }

    fun setupNegativeSpaceShape() {

        val negativeSpaceLayers = getDrawable(R.drawable.negative_space_shape) as LayerDrawable

        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        backgroundShadowRadius[0] = (51).toFloat()//topLeftCorner
        backgroundShadowRadius[1] = (51).toFloat()//topLeftCorner

        backgroundShadowRadius[2] = (51).toFloat()//topRightCorner
        backgroundShadowRadius[3] = (51).toFloat()//topRightCorner

        backgroundShadowRadius[4] = (51).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (51).toFloat()//bottomRightCorner

        backgroundShadowRadius[6] = (51).toFloat()//bottomLeftCorner
        backgroundShadowRadius[7] = (51).toFloat()//bottomLeftCorner

        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
        val shapeShadowPaint = shapeShadow.paint.apply {
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
            alpha = 155

            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

//        val negativeShapeDrawable = negativeSpaceLayers.findDrawableByLayerId(R.id.clearLayer).toBitmap()


        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, shapeShadow)

        negativeSpaceDesign.setLayerType(ImageView.LAYER_TYPE_HARDWARE, shapeShadowPaint)

        negativeSpaceDesign.setImageDrawable(negativeSpaceLayers)

    }
}