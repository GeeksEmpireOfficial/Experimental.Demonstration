package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.sin


class CurveLayoutManager(private val context: Context,
                         private var horizontalOffset: Int = 0) : RecyclerView.LayoutManager() {

    init {




    }

    var canScroll = true

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    override fun canScrollHorizontally(): Boolean {
        super.canScrollHorizontally()

        return canScroll
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        horizontalOffset += dx

        fill(recycler, state)

        return dx
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

        fill(recycler, state)

    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        super.smoothScrollToPosition(recyclerView, state, position)
    }

    override fun getChildCount(): Int {
        return super.getChildCount()
    }

    override fun getPosition(view: View): Int {
        return super.getPosition(view)
    }

    override fun scrollToPosition(position: Int) {
        super.scrollToPosition(position)

    }

    private fun fill(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {

        detachAndScrapAttachedViews(recycler ?: return)

        for (itemIndex in 0 until itemCount) {

            val view = recycler.getViewForPosition(itemIndex)

            addView(view)

            val viewWidth = pxFromDp(context, 333f)
            val viewHeight = pxFromDp(context, 512f)

            val left = (itemIndex * viewWidth) - horizontalOffset
            val right = left + viewWidth
            val top = computeYComponent((left + right) / 2, viewHeight)
            val bottom = top.first + viewHeight

            val alpha = top.second
            view.rotation = (alpha * (180 / PI)).toFloat() - 90f

            measureChildWithMargins(view ?: return, viewWidth.toInt(), viewHeight.toInt())
            layoutDecoratedWithMargins(
                view,
                left.toInt(),
                top.first.toInt(),
                right.toInt(),
                bottom.toInt()
            )



        }

        recycler.scrapList.toList().forEach {
            recycler.recycleView(it.itemView)
        }
    }

    private fun computeYComponent(viewCenterX: Float,
                                  h: Float): Pair<Int, Double> {

        val screenWidth = context.resources.displayMetrics.widthPixels

        val s = (screenWidth.toDouble() / 2)

        val radius = ((h * h + s * s) / (h * 2)) + dpToPixel(666f)

        val xScreenFraction = viewCenterX.toDouble() / screenWidth.toDouble()
        val beta = acos(s / radius)

        val alpha = beta + (xScreenFraction * (Math.PI - (2 * beta)))
        val yComponent = radius - (radius * sin(alpha))

        return Pair(yComponent.toInt(), alpha)
    }

    private fun pxFromDp(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    private fun dpToPixel(dp: Float): Float {
        val resources = context.resources
        val metrics = resources.displayMetrics
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}