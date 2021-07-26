package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageFirstBinding

class FluidFragmentFirst : Fragment() {

    lateinit var uiFluidPageFirstBinding: UiFluidPageFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageFirstBinding = UiFluidPageFirstBinding.inflate(layoutInflater)

        return uiFluidPageFirstBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageFirstBinding.textView.append(" 1")

    }

}