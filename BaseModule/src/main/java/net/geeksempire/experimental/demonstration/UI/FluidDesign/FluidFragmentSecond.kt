package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageSecondBinding

class FluidFragmentSecond : Fragment() {

    lateinit var uiFluidPageSecondBinding: UiFluidPageSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageSecondBinding = UiFluidPageSecondBinding.inflate(layoutInflater)

        return uiFluidPageSecondBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageSecondBinding.textView.append(" 2")

    }

}