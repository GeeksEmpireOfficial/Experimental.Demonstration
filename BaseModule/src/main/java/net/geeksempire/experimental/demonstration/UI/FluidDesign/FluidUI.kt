package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.R
import net.geeksempire.experimental.demonstration.databinding.UiFluidBinding

class FluidUI : AppCompatActivity() {

    lateinit var uiFluidBinding: UiFluidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFluidBinding = UiFluidBinding.inflate(layoutInflater)
        setContentView(uiFluidBinding.root)

        val fluidPagerAdapter = FluidPagerAdapter(supportFragmentManager)

        uiFluidBinding.liquidPage.post {

            uiFluidBinding.liquidPage.adapter = fluidPagerAdapter

            uiFluidBinding.liquidPage.scrollTo(0, 0)

            uiFluidBinding.liquidPage.setButtonDrawable(R.drawable.android)

            fluidPagerAdapter.notifyDataSetChanged()

        }

    }

}