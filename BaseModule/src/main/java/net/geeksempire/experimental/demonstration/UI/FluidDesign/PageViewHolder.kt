package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ui_fluid_page_item.view.*

class PageViewHolder (rootView: View) : RecyclerView.ViewHolder(rootView) {
    val rootViewItem: ConstraintLayout = rootView.rootViewItem

    val textView: TextView = rootView.textView
}