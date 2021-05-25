
package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.*
import android.graphics.drawable.*
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R


class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

        if (vectorControlOne.isShown) {

            val animatable = getDrawable(R.drawable.animated_geeksempire) as Animatable
            animatable.start()

            vectorControlOne.setImageDrawable(animatable as Drawable)

        }

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        rootView.setBackgroundColor(Color.TRANSPARENT)

        window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.TR_BL,
            intArrayOf(Color.BLUE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.RED, Color.MAGENTA)))

        with(negativeSpaceDesign) {

//            setupNegativeSpaceShape()
            setNegativeSpaceVectorDrawable()

        }

        negativeSpaceDesign.setOnClickListener {



        }

    }

    fun setupNegativeSpaceShape() {

        val negativeSpaceLayers = getDrawable(R.drawable.negative_space_shape) as LayerDrawable

        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        backgroundShadowRadius[0] = (99).toFloat()//topLeftCorner
        backgroundShadowRadius[1] = (99).toFloat()//topLeftCorner

        backgroundShadowRadius[2] = (99).toFloat()//topRightCorner
        backgroundShadowRadius[3] = (99).toFloat()//topRightCorner

        backgroundShadowRadius[6] = (99).toFloat()//bottomLeftCorner
        backgroundShadowRadius[7] = (99).toFloat()//bottomLeftCorner

        backgroundShadowRadius[4] = (23).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (23).toFloat()//bottomRightCorner

        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
        shapeShadow.paint.apply {
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
            isAntiAlias = true

            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, shapeShadow)

        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, shapeShadow.paint)
        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

        negativeSpaceDesign.setImageDrawable(negativeSpaceLayers)

    }

    fun setShadow(negativeSpaceLayers: LayerDrawable) {

        val backgroundShadowRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)

        backgroundShadowRadius[0] = (19).toFloat()//topLeftCorner
        backgroundShadowRadius[1] = (19).toFloat()//topLeftCorner

        backgroundShadowRadius[2] = (19).toFloat()//topRightCorner
        backgroundShadowRadius[3] = (19).toFloat()//topRightCorner

        backgroundShadowRadius[6] = (19).toFloat()//bottomLeftCorner
        backgroundShadowRadius[7] = (19).toFloat()//bottomLeftCorner

        backgroundShadowRadius[4] = (19).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (19).toFloat()//bottomRightCorner

        val shapeShadow: ShapeDrawable = ShapeDrawable(RoundRectShape(backgroundShadowRadius, null, null))
        shapeShadow.paint.apply {
            color = getColor(R.color.dark)

            setShadowLayer(29f, 0f, 0f, getColor(R.color.dark))
        }

        negativeSpaceLayers.setDrawableByLayerId(R.id.backgroundLayer, shapeShadow)

        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, shapeShadow.paint)
        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

    }

    fun setNegativeSpaceVectorDrawable() {

        val negativeSpaceLayers = getDrawable(R.drawable.negative_space_shape) as LayerDrawable
        val clearLayer = negativeSpaceLayers.findDrawableByLayerId(R.id.clearLayer)

        val vectorShape = getDrawable(R.drawable.android) as VectorDrawable

        val vectorBitmap = vectorShape.toBitmap()

        val canvas = Canvas(vectorBitmap)

        negativeSpaceLayers.setBounds(0, 0, canvas.width, canvas.height)

        val paint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.TRANSPARENT
            isAntiAlias = true

            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        }

        canvas.drawBitmap(vectorBitmap, 0f, 0f, null)

//        clearLayer.draw(canvas)

        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, vectorBitmap.toDrawable(resources))
        negativeSpaceLayers.setDrawableByLayerId(R.id.clearLayer, /*vectorBitmap.toDrawable(resources)*/clearLayer)

        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, paint)
        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

        negativeSpaceDesign.setImageDrawable(negativeSpaceLayers)
//        negativeSpaceDesign.setImageBitmap(vectorBitmap)

    }

}