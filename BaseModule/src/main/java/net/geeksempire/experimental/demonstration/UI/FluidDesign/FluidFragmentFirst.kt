package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageFirstBinding

class FluidFragmentFirst : Fragment() {

    lateinit var uiFluidPageFirstBinding: UiFluidPageFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageFirstBinding = UiFluidPageFirstBinding.inflate(layoutInflater)

        return uiFluidPageFirstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageFirstBinding.negativeSpaceDesign.post {

            val baseBitmap = Bitmap.createBitmap(uiFluidPageFirstBinding.negativeSpaceDesign.width, uiFluidPageFirstBinding.negativeSpaceDesign.height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(baseBitmap)

            canvas.drawRoundRect(
                RectF(0f, 0f, (uiFluidPageFirstBinding.negativeSpaceDesign.width.toFloat()), uiFluidPageFirstBinding.negativeSpaceDesign.height.toFloat()),
                99f, 99f, Paint().apply { color = Color.CYAN })

            val paint = Paint().apply {
                style = Paint.Style.FILL
                color = Color.BLACK
                isAntiAlias = true
                textSize = 51f

                xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            }
            canvas.drawText("Geeks Empire", 29f, 50f, paint)

            uiFluidPageFirstBinding.negativeSpaceDesign.setImageBitmap(baseBitmap)

        }

    }

}