package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.VectorDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.ui_material.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_material)

//        if (vectorControlOne.isShown) {
//
//            val animatable = getDrawable(R.drawable.animated_geeksempire) as Animatable
//            animatable.start()
//
//            vectorControlOne.setImageDrawable(animatable as Drawable)
//
//        }

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        rootView.setBackgroundColor(Color.TRANSPARENT)

        window.setBackgroundDrawable(GradientDrawable(GradientDrawable.Orientation.TR_BL,
            intArrayOf(Color.BLUE, Color.GREEN, Color.CYAN, Color.RED, Color.MAGENTA)))

//        with(negativeSpaceDesign) {
//            setupNegativeSpaceShape()
//            setNegativeSpaceVectorDrawable()
//        }

//        negativeSpaceDesign.setOnClickListener { }

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

        backgroundShadowRadius[4] = (99).toFloat()//bottomRightCorner
        backgroundShadowRadius[5] = (99).toFloat()//bottomRightCorner

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

        val vectorShape = (getDrawable(R.drawable.android) as VectorDrawable)
        val shapeBitmap = vectorShape.toBitmap()

        val vectorBitmap = Bitmap.createBitmap(shapeBitmap.width, shapeBitmap.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(vectorBitmap)

        canvas.drawRoundRect(RectF(0f, 0f, vectorShape.intrinsicWidth.toFloat(), vectorShape.intrinsicHeight.toFloat()),
            99f, 99f, Paint().apply { color = Color.BLUE })

        val paint = Paint().apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            isAntiAlias = true

            xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
        }
        canvas.drawBitmap(shapeBitmap, 0f, 0f, paint)

        vectorShape.draw(canvas)

        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_SOFTWARE, paint)
        negativeSpaceDesign.setLayerType(AppCompatButton.LAYER_TYPE_HARDWARE, null)

        negativeSpaceDesign.setImageBitmap(vectorBitmap)
//        negativeSpaceDesign.background = (vectorBitmap).toDrawable(resources)

    }

}