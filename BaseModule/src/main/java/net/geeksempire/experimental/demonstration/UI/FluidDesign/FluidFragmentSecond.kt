package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageSecondBinding

class FluidFragmentSecond : Fragment() {

    lateinit var uiFluidPageSecondBinding: UiFluidPageSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageSecondBinding = UiFluidPageSecondBinding.inflate(layoutInflater)

        return uiFluidPageSecondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageSecondBinding.negativeSpaceDesign.post {

            val vectorBitmap = Bitmap.createBitmap(uiFluidPageSecondBinding.negativeSpaceDesign.width, uiFluidPageSecondBinding.negativeSpaceDesign.height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(vectorBitmap)

            canvas.drawRoundRect(
                RectF(0f, 0f, (uiFluidPageSecondBinding.negativeSpaceDesign.width.toFloat()), uiFluidPageSecondBinding.negativeSpaceDesign.height.toFloat()),
                99f, 99f, Paint().apply { color = Color.RED })

            val paint = Paint().apply {
                style = Paint.Style.FILL
                color = Color.BLACK
                isAntiAlias = true
                textSize = 51f

                xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            }
            canvas.drawText("Geeks Empire", 29f, 50f, paint)

            uiFluidPageSecondBinding.negativeSpaceDesign.setImageBitmap(vectorBitmap)

        }

    }

}