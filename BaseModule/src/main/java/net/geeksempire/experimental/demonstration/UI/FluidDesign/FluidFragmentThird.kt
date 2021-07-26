package net.geeksempire.experimental.demonstration.UI.FluidDesign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.geeksempire.experimental.demonstration.databinding.UiFluidPageThirdBinding

class FluidFragmentThird : Fragment() {

    lateinit var uiFluidPageThirdBinding: UiFluidPageThirdBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        uiFluidPageThirdBinding = UiFluidPageThirdBinding.inflate(layoutInflater)

        return uiFluidPageThirdBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiFluidPageThirdBinding.textView.append(" 3")

    }

}