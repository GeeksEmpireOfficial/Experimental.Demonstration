package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GeekyLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {

        val layoutParameters = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return layoutParameters
    }

    override fun canScrollHorizontally(): Boolean = true

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {



        return dx
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, recyclerViewState: RecyclerView.State?) {
        super.onLayoutChildren(recycler, recyclerViewState)



    }

}