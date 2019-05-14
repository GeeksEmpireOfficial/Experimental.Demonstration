package com.geekstest.dynamicfeaturedemo

import android.content.Context

class BaseFunctionsClass(context: Context) {

    lateinit var context: Context

    init {
        this.context = context
    }

    fun baseFunctions() {
        println("*** ${context.packageName} ***")
    }
}