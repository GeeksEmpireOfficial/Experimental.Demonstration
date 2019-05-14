package com.geekstest.dynamicfeatureondemand

import android.content.Context

class DynamicFunctionsClass {

    constructor(context: Context) {

    }

    init {

    }

    fun doSomethingFromDynamic(aNumber: Int) {
        var resultNumber = DynamicActivity.xNumber + aNumber;
        println("*** ${resultNumber}")
    }
}