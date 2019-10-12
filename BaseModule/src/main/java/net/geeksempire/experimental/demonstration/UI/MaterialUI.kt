package net.geeksempire.experimental.demonstration.UI

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.progress_bars.*
import net.geeksempire.experimental.demonstration.R

class MaterialUI : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.progress_bars)

        progressBar.progress = 77f
    }
}