package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.widget.ViewPager2
import net.geeksempire.experimental.demonstration.databinding.UiFluidBinding

class FluidUI : AppCompatActivity() {

    lateinit var uiFluidBinding: UiFluidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFluidBinding = UiFluidBinding.inflate(layoutInflater)
        setContentView(uiFluidBinding.root)

        val fluidPagerAdapter: FluidPagerAdapter = FluidPagerAdapter(this@FluidUI)



        uiFluidBinding.viewPager.post {

            fluidPagerAdapter.listOfFragment.clear()

            fluidPagerAdapter.listOfFragment.add("111")
            fluidPagerAdapter.listOfFragment.add("222")
            fluidPagerAdapter.listOfFragment.add("333")
            fluidPagerAdapter.listOfFragment.add("444")
            fluidPagerAdapter.listOfFragment.add("555")
            fluidPagerAdapter.listOfFragment.add("666")
            fluidPagerAdapter.listOfFragment.add("777")
            fluidPagerAdapter.listOfFragment.add("888")
            fluidPagerAdapter.listOfFragment.add("999")

            uiFluidBinding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            uiFluidBinding.viewPager.adapter = fluidPagerAdapter

        }

        uiFluidBinding.curveRecyclerView.post {

            fluidPagerAdapter.listOfFragment.clear()

            fluidPagerAdapter.listOfFragment.add("111")
            fluidPagerAdapter.listOfFragment.add("222")
            fluidPagerAdapter.listOfFragment.add("333")
            fluidPagerAdapter.listOfFragment.add("444")
            fluidPagerAdapter.listOfFragment.add("555")
            fluidPagerAdapter.listOfFragment.add("666")
            fluidPagerAdapter.listOfFragment.add("777")
            fluidPagerAdapter.listOfFragment.add("888")
            fluidPagerAdapter.listOfFragment.add("999")

            uiFluidBinding.curveRecyclerView.layoutManager = CurveLayoutManager(applicationContext, horizontalOffset = 3)
            uiFluidBinding.curveRecyclerView.adapter = fluidPagerAdapter

            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(uiFluidBinding.curveRecyclerView)

        }

    }

}