package com.geekstest.dynamicfeatureondemand

import android.content.Context
import com.geekstest.dynamicfeaturedemo.BaseConfigurations

class DynamicFunctionsClass : BaseConfigurations {

    lateinit var context: Context

    constructor(context: Context) {

    }

    init {
        this.context = context
    }

    fun doSomethingFromDynamic(aNumber: Int) {
        var resultNumber = DynamicActivity.xNumber + aNumber;
        println("*** ${resultNumber}")
    }
}