package net.geeksempire.experimental.demonstration.UI.Painting

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Canvas(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    // Get height or width of screen
    //var screenHeight: Int = DeviceDimensionsHelper.getDisplayHeight(this)
    //var screenWidth: Int = DeviceDimensionsHelper.getDisplayWidth(this)
    // Convert dp to pixels
    //var px: Float = DeviceDimensionsHelper.convertDpToPixel(25f, this)
    // Convert pixels to dp
    //var dp: Float = DeviceDimensionsHelper.convertPixelsToDp(25f, this)

    private val paintColor: Int = Color.WHITE

    private val drawPaint: Paint = Paint()

    private val path: Path = Path()

    init {
        isFocusable = true
        isFocusableInTouchMode = true

        setupPaint()
    }

    private fun setupPaint() {

        drawPaint.color = paintColor
        drawPaint.strokeWidth = 5.0f

        drawPaint.isAntiAlias = true

        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawPath(path, drawPaint)

    }

    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {

        motionEvent?.let {

            val pointX: Float = motionEvent.x
            val pointY: Float = motionEvent.y

            println(">>>>>>>>> ${pointX} --- ${pointY}")

            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {

                    path.moveTo(pointX, pointY)

                }
                MotionEvent.ACTION_MOVE -> {


                    path.lineTo(pointX, pointY)

                }
                else -> {

                }
            }

        }

        postInvalidate()

        return true
    }

}