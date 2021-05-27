package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageThirdBinding

class FluidFragmentThird : Fragment() {

    lateinit var uiFluidPageThirdBinding: UiFluidPageThirdBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageThirdBinding = UiFluidPageThirdBinding.inflate(layoutInflater)

        return uiFluidPageThirdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageThirdBinding.negativeSpaceDesign.post {

            val vectorBitmap = Bitmap.createBitmap(uiFluidPageThirdBinding.negativeSpaceDesign.width, uiFluidPageThirdBinding.negativeSpaceDesign.height, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(vectorBitmap)

            canvas.drawRoundRect(
                RectF(0f, 0f, (uiFluidPageThirdBinding.negativeSpaceDesign.width.toFloat()), uiFluidPageThirdBinding.negativeSpaceDesign.height.toFloat()),
                99f, 99f, Paint().apply { color = Color.RED })

            val paint = Paint().apply {
                style = Paint.Style.FILL
                color = Color.BLACK
                isAntiAlias = true
                textSize = 51f

                xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)
            }
            canvas.drawText("Geeks Empire", 29f, 50f, paint)

            uiFluidPageThirdBinding.negativeSpaceDesign.setImageBitmap(vectorBitmap)

        }

    }

}