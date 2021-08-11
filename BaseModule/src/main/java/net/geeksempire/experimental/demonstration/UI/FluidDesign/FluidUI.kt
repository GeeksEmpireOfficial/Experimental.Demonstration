package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.viewpager2.widget.ViewPager2
import net.geeksempire.experimental.demonstration.UI.CurveLayoutManager.FanChildDrawingOrderCallback
import net.geeksempire.experimental.demonstration.UI.CurveLayoutManager.FanLayoutManager
import net.geeksempire.experimental.demonstration.UI.CurveLayoutManager.FanLayoutManagerSettings
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
            fluidPagerAdapter.listOfFragment.add("000")
            fluidPagerAdapter.listOfFragment.add("111")
            fluidPagerAdapter.listOfFragment.add("222")
            fluidPagerAdapter.listOfFragment.add("333")
            fluidPagerAdapter.listOfFragment.add("444")
            fluidPagerAdapter.listOfFragment.add("555")
            fluidPagerAdapter.listOfFragment.add("666")
            fluidPagerAdapter.listOfFragment.add("777")
            fluidPagerAdapter.listOfFragment.add("888")
            fluidPagerAdapter.listOfFragment.add("999")
            fluidPagerAdapter.listOfFragment.add("AAA")
            fluidPagerAdapter.listOfFragment.add("BBB")
            fluidPagerAdapter.listOfFragment.add("CCC")
            fluidPagerAdapter.listOfFragment.add("DDD")
            fluidPagerAdapter.listOfFragment.add("EEE")
            fluidPagerAdapter.listOfFragment.add("FFF")
            fluidPagerAdapter.listOfFragment.add("GGG")
            fluidPagerAdapter.listOfFragment.add("HHH")
            fluidPagerAdapter.listOfFragment.add("III")
            fluidPagerAdapter.listOfFragment.add("JJJ")
            fluidPagerAdapter.listOfFragment.add("KKK")
            fluidPagerAdapter.listOfFragment.add("LLL")
            fluidPagerAdapter.listOfFragment.add("MMM")
            fluidPagerAdapter.listOfFragment.add("NNN")
            fluidPagerAdapter.listOfFragment.add("OOO")
            fluidPagerAdapter.listOfFragment.add("PPP")
            fluidPagerAdapter.listOfFragment.add("QQQ")
            fluidPagerAdapter.listOfFragment.add("RRR")
            fluidPagerAdapter.listOfFragment.add("SSS")
            fluidPagerAdapter.listOfFragment.add("TTT")
            fluidPagerAdapter.listOfFragment.add("UUU")
            fluidPagerAdapter.listOfFragment.add("VVV")
            fluidPagerAdapter.listOfFragment.add("WWW")
            fluidPagerAdapter.listOfFragment.add("XXX")
            fluidPagerAdapter.listOfFragment.add("YYY")
            fluidPagerAdapter.listOfFragment.add("ZZZ")

            uiFluidBinding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            uiFluidBinding.viewPager.adapter = fluidPagerAdapter

            uiFluidBinding.viewPager.setPageTransformer { page, position ->

                val width = page.width
                val height = page.height

                val rotation = -13f * position * -1.25f

                page.pivotX = width * 0.5f
                page.pivotY = height.toFloat()
                page.rotation = rotation

            }

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

            val fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(applicationContext)
                .withFanRadius(true)
                .withViewWidthDp(333f)
                .withViewHeightDp(512f)
                .build()

            val curveLayoutManager = FanLayoutManager(applicationContext, fanLayoutManagerSettings)

            val curvedAdapter: CurvedAdapter = CurvedAdapter(this@FluidUI)
            curvedAdapter.listOfFragment = listOfFragment

            uiFluidBinding.curveRecyclerView.layoutManager = curveLayoutManager
            uiFluidBinding.curveRecyclerView.itemAnimator = DefaultItemAnimator()

            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(uiFluidBinding.curveRecyclerView)

            uiFluidBinding.curveRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {

                            val currentItemPosition = curveLayoutManager.findCurrentCenterViewPos()


                        }
                    }

                }

            })

            uiFluidBinding.curveRecyclerView.adapter = curvedAdapter

            uiFluidBinding.curveRecyclerView.setChildDrawingOrderCallback(FanChildDrawingOrderCallback(curveLayoutManager));

        }

    }

}