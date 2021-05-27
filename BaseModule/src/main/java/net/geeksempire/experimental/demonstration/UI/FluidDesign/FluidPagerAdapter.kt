package net.geeksempire.experimental.demonstration.UI.FluidDesign

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FluidPagerAdapter (fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val listOfFragment = ArrayList<Fragment>()

    override fun getCount(): Int {

        return 2
    }

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {

                FluidFragmentFirst()

            }
            1 -> {

                FluidFragmentSecond()

            }
            else -> {

                FluidFragmentFirst()

            }
        }
    }


}