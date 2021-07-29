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

        val curvedAdapter: CurvedAdapter = CurvedAdapter(this@FluidUI)

        uiFluidBinding.curveRecyclerView.post {

            curvedAdapter.listOfFragment.clear()

            curvedAdapter.listOfFragment.add("000")
            curvedAdapter.listOfFragment.add("111")
            curvedAdapter.listOfFragment.add("222")
            curvedAdapter.listOfFragment.add("333")
            curvedAdapter.listOfFragment.add("444")
            curvedAdapter.listOfFragment.add("555")
            curvedAdapter.listOfFragment.add("666")
            curvedAdapter.listOfFragment.add("777")
            curvedAdapter.listOfFragment.add("888")
            curvedAdapter.listOfFragment.add("999")
            curvedAdapter.listOfFragment.add("AAA")
            curvedAdapter.listOfFragment.add("BBB")
            curvedAdapter.listOfFragment.add("CCC")
            curvedAdapter.listOfFragment.add("DDD")
            curvedAdapter.listOfFragment.add("EEE")
            curvedAdapter.listOfFragment.add("FFF")
            curvedAdapter.listOfFragment.add("GGG")
            curvedAdapter.listOfFragment.add("HHH")
            curvedAdapter.listOfFragment.add("III")
            curvedAdapter.listOfFragment.add("JJJ")
            curvedAdapter.listOfFragment.add("KKK")
            curvedAdapter.listOfFragment.add("LLL")
            curvedAdapter.listOfFragment.add("MMM")
            curvedAdapter.listOfFragment.add("NNN")
            curvedAdapter.listOfFragment.add("OOO")
            curvedAdapter.listOfFragment.add("PPP")
            curvedAdapter.listOfFragment.add("QQQ")
            curvedAdapter.listOfFragment.add("RRR")
            curvedAdapter.listOfFragment.add("SSS")
            curvedAdapter.listOfFragment.add("TTT")
            curvedAdapter.listOfFragment.add("UUU")
            curvedAdapter.listOfFragment.add("VVV")
            curvedAdapter.listOfFragment.add("WWW")
            curvedAdapter.listOfFragment.add("XXX")
            curvedAdapter.listOfFragment.add("YYY")
            curvedAdapter.listOfFragment.add("ZZZ")

            val curveLayoutManager = CurveLayoutManager(context = applicationContext)
            uiFluidBinding.curveRecyclerView.layoutManager = curveLayoutManager
            uiFluidBinding.curveRecyclerView.adapter = curvedAdapter

            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(uiFluidBinding.curveRecyclerView)

        }

    }

}