package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GeekyLayoutManager : RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {

        val layoutParameters = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        return layoutParameters
    }

}