package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        val animatable = getDrawable(R.drawable.animated_geeksempire) as Animatable
        animatable.start()

        vectorControlOne.setImageDrawable(animatable as Drawable)

//        val gradientDrawableBackground = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN))
//
//        rootView.background = gradientDrawableBackground

        with(negativeSpaceDesign) {

            setupNegativeSpaceShape()

        }

        negativeSpaceDesign.setOnClickListener {



        }

    }

    fun setupNegativeSpaceShape() {

        val negativeSpaceLayers = getDrawable(R.drawable.layer_shodow) as LayerDrawable

//        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
//
//        backgroundShadowRadius[0] = (99).toFloat()//topLeftCorner
//        backgroundShadowRadius[1] = (99).toFloat()//topLeftCorner
//
//        backgroundShadowRadius[2] = (99).toFloat()//topRightCorner
//        backgroundShadowRadius[3] = (99).toFloat()//topRightCorner
//
//        backgroundShadowRadius[6] = (99).toFloat()//bottomLeftCorner
//        backgroundShadowRadius[7] = (99).toFloat()//bottomLeftCorner
//
//        backgroundShadowRadius[4] = (19).toFloat()//bottomRightCorner
//        backgroundShadowRadius[5] = (19).toFloat()//bottomRightCorner
//
//        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
//        shapeShadow.paint.apply {
//            style = Paint.Style.FILL
//            color = Color.TRANSPARENT
//            isAntiAlias = true
//
//            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
//        }
//
//        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, shapeShadow)

        setShadow(negativeSpaceLayers)

//        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, shapeShadow.paint)
//        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

        negativeSpaceDesign.setImageDrawable(negativeSpaceLayers)

    }

    fun setShadow(negativeSpaceLayers: LayerDrawable) {

        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        backgroundShadowRadius[0] = (399).toFloat()//topLeftCorner
        backgroundShadowRadius[1] = (399).toFloat()//topLeftCorner

        backgroundShadowRadius[2] = (399).toFloat()//topRightCorner
        backgroundShadowRadius[3] = (399).toFloat()//topRightCorner

        backgroundShadowRadius[6] = (399).toFloat()//bottomLeftCorner
        backgroundShadowRadius[7] = (399).toFloat()//bottomLeftCorner

        backgroundShadowRadius[4] = (399).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (399).toFloat()//bottomRightCorner

        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
        shapeShadow.paint.apply {
            color = getColor(R.color.dark)

            setShadowLayer(29f, 0f, 0f, getColor(R.color.dark))
        }

        negativeSpaceLayers.setDrawableByLayerId(R.id.circleLayer, shapeShadow)

        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, shapeShadow.paint)
        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

    }

}