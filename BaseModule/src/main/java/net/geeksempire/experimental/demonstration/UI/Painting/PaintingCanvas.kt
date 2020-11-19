package net.geeksempire.experimental.demonstration.UI.Painting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.PaintingViewBinding

class PaintingCanvas : AppCompatActivity() {

    lateinit var paintingViewBinding: PaintingViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paintingViewBinding = PaintingViewBinding.inflate(layoutInflater)
        setContentView(paintingViewBinding.root)

    }

}