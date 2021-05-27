package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.UiFluidBinding

class FluidUI : AppCompatActivity() {

    lateinit var uiFluidBinding: UiFluidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFluidBinding = UiFluidBinding.inflate(layoutInflater)
        setContentView(uiFluidBinding.root)

        val fluidPagerAdapter: FluidPagerAdapter = FluidPagerAdapter(supportFragmentManager)

        uiFluidBinding.viewPager.post {

            fluidPagerAdapter.listOfFragment.add(FluidFragmentFirst())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentSecond())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentThird())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentFirst())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentSecond())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentThird())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentFirst())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentSecond())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentThird())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentFirst())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentSecond())
            fluidPagerAdapter.listOfFragment.add(FluidFragmentThird())

            uiFluidBinding.viewPager.adapter = fluidPagerAdapter

        }

    }

}