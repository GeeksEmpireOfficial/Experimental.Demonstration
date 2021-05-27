package net.geeksempire.experimental.demonstration.UI.FluidDesign

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class FluidPagerAdapter (fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

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