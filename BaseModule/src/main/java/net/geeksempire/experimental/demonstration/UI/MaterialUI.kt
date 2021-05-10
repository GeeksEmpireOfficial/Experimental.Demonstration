package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.*
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

        val gradientDrawableBackground = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN))

        rootView.background = gradientDrawableBackground

        val negativeSpaceLayers = getDrawable(R.drawable.negative_space_shape) as LayerDrawable

        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        backgroundShadowRadius[0] = (99).toFloat()//topLeftCorner
        backgroundShadowRadius[1] = (99).toFloat()//topLeftCorner

        backgroundShadowRadius[2] = (99).toFloat()//topRightCorner
        backgroundShadowRadius[3] = (99).toFloat()//topRightCorner

        backgroundShadowRadius[6] = (99).toFloat()//bottomLeftCorner
        backgroundShadowRadius[7] = (99).toFloat()//bottomLeftCorner

        backgroundShadowRadius[4] = (19).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (19).toFloat()//bottomRightCorner

        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
        shapeShadow.paint.apply {
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
            isAntiAlias = true

            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, shapeShadow)

        negativeSpaceDesign.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, shapeShadow.paint)
        negativeSpaceDesign.setLayerType(ImageView.LAYER_TYPE_HARDWARE, null)

        negativeSpaceDesign.setImageDrawable(negativeSpaceLayers)

        negativeSpaceDesign.setOnClickListener {



        }

    }
}