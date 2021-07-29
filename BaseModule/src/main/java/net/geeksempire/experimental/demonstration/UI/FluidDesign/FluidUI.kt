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

            val listOfFragment = ArrayList<String>()

            listOfFragment.clear()

            listOfFragment.add("000")
            listOfFragment.add("111")
            listOfFragment.add("222")
            listOfFragment.add("333")
            listOfFragment.add("444")
            listOfFragment.add("555")
            listOfFragment.add("666")
            listOfFragment.add("777")
            listOfFragment.add("888")
            listOfFragment.add("999")
            listOfFragment.add("AAA")
            listOfFragment.add("BBB")
            listOfFragment.add("CCC")
            listOfFragment.add("DDD")
            listOfFragment.add("EEE")
            listOfFragment.add("FFF")
            listOfFragment.add("GGG")
            listOfFragment.add("HHH")
            listOfFragment.add("III")
            listOfFragment.add("JJJ")
            listOfFragment.add("KKK")
            listOfFragment.add("LLL")
            listOfFragment.add("MMM")
            listOfFragment.add("NNN")
            listOfFragment.add("OOO")
            listOfFragment.add("PPP")
            listOfFragment.add("QQQ")
            listOfFragment.add("RRR")
            listOfFragment.add("SSS")
            listOfFragment.add("TTT")
            listOfFragment.add("UUU")
            listOfFragment.add("VVV")
            listOfFragment.add("WWW")
            listOfFragment.add("XXX")
            listOfFragment.add("YYY")
            listOfFragment.add("ZZZ")

            val curveLayoutManager = CurveLayoutManager(context = applicationContext)

            val curvedAdapter: CurvedAdapter = CurvedAdapter(this@FluidUI)
            curvedAdapter.listOfFragment = listOfFragment

            uiFluidBinding.curveRecyclerView.layoutManager = curveLayoutManager
            uiFluidBinding.curveRecyclerView.adapter = curvedAdapter

            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(uiFluidBinding.curveRecyclerView)

            uiFluidBinding.curveRecyclerView.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->

                println(">>> X: $scrollX | Y: $scrollY")



            }

        }

    }

}