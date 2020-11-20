package net.geeksempire.experimental.demonstration.UI.Painting

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.R
import net.geeksempire.experimental.demonstration.databinding.PaintingViewBinding

class PaintingCanvas : AppCompatActivity() {

    var keyboardFocus: Boolean = false

    lateinit var paintingViewBinding: PaintingViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paintingViewBinding = PaintingViewBinding.inflate(layoutInflater)
        setContentView(paintingViewBinding.root)

        window.statusBarColor = getColor(R.color.light)
        window.navigationBarColor = getColor(R.color.light)

        paintingViewBinding.drawingCanvasHolder.addView(Canvas(applicationContext).also {
            it.setupPaintingPanel(
                getColor(R.color.default_color_game_light),
                10.0f
            )
        })

        paintingViewBinding.toggleKeyboardView.setOnClickListener {

            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            if (keyboardFocus) {

                keyboardFocus = false

                inputMethodManager.hideSoftInputFromWindow(paintingViewBinding.editTextView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                paintingViewBinding.editTextView.clearFocus()

            } else {

                keyboardFocus = true

                inputMethodManager.showSoftInput(paintingViewBinding.editTextView, InputMethodManager.SHOW_IMPLICIT)

                paintingViewBinding.editTextView.requestFocus()

            }

        }

    }

}