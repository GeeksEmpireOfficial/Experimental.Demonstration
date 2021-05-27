package net.geeksempire.experimental.demonstration.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.UiFluidBinding

class FluidUI : AppCompatActivity() {

    lateinit var uiFluidBinding: UiFluidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiFluidBinding = UiFluidBinding.inflate(layoutInflater)
        setContentView(uiFluidBinding.root)



    }

}