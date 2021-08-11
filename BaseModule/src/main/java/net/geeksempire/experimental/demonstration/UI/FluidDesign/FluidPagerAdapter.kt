package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import net.geeksempire.experimental.demonstration.R

class FluidPagerAdapter (val context: AppCompatActivity): RecyclerView.Adapter<PageViewHolder>() {

    val listOfFragment = ArrayList<String>()

    val colors = arrayOf(Color.RED, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.BLACK, Color.WHITE, Color.YELLOW, Color.DKGRAY, Color.GRAY, Color.GREEN)

    override fun getItemCount(): Int {

        return listOfFragment.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PageViewHolder {

        return PageViewHolder(LayoutInflater.from(context).inflate(R.layout.ui_fluid_page_item, viewGroup, false))
    }

    override fun onBindViewHolder(pageViewHolder: PageViewHolder, position: Int) {

        val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TR_BL, intArrayOf(colors.random(), colors.random()))
        gradientDrawable.cornerRadius = 51f

        pageViewHolder.textView.background = gradientDrawable

        pageViewHolder.textView.text = listOfFragment[position]

    }

}