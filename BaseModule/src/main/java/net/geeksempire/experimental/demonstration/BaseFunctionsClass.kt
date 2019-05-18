package net.geeksempire.experimental.demonstration

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