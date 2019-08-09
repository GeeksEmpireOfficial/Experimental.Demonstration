package net.geeksempire.experimental.demonstration.Utils.Functions

import android.content.Context
import android.util.DisplayMetrics

class FunctionsClass(context: Context) {

    lateinit var context: Context

    init {
        this.context = context
    }

    fun DpToPixel(dp: Float): Float {
        val resources = context.getResources()
        val metrics = resources.getDisplayMetrics()
        return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}