package net.geeksempire.experimental.demonstration.UI.Painting

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.R
import net.geeksempire.experimental.demonstration.databinding.PaintingViewBinding
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class PaintingCanvas : AppCompatActivity() {

    var keyboardFocus: Boolean = false

    lateinit var paintingViewBinding: PaintingViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paintingViewBinding = PaintingViewBinding.inflate(layoutInflater)
        setContentView(paintingViewBinding.root)

        window.statusBarColor = getColor(R.color.light)
        window.navigationBarColor = getColor(R.color.light)

        paintingViewBinding.drawingCanvasHolder.addView(PaintingCanvasView(applicationContext).also {
            it.setupPaintingPanel(
                getColor(R.color.default_color_game_light),
                10.0f
            )
        })

        paintingViewBinding.toggleKeyboardView.setOnClickListener {

            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            if (keyboardFocus) {

                keyboardFocus = false

                inputMethodManager.hideSoftInputFromWindow(
                    paintingViewBinding.editTextView.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )

                paintingViewBinding.editTextView.clearFocus()

            } else {

                keyboardFocus = true

                inputMethodManager.showSoftInput(
                    paintingViewBinding.editTextView,
                    InputMethodManager.SHOW_IMPLICIT
                )

                paintingViewBinding.editTextView.requestFocus()

            }

        }

        paintingViewBinding.keepNoteView.setOnClickListener {

            takeScreenShot(paintingViewBinding.root)

        }

    }

    fun takeScreenShot(view: View) : Bitmap {

        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        view.draw(canvas)

        storeImage(bitmap)

        return bitmap

    }

    private fun storeImage(image: Bitmap) {

        val pictureFile: File? = getOutputMediaFile()

        if (pictureFile != null) {

            try {
                val fileOutputStream = FileOutputStream(pictureFile)
                image.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream)
                fileOutputStream.close()

            } catch (e: FileNotFoundException) {

            } catch (e: IOException) {

            }

        }

    }

    private fun getOutputMediaFile(): File? {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        val mediaStorageDir: File = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files"
        )

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        // Create a media file name
        val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmm").format(Date())
        val mediaFile: File
        val mImageName = "MI_$timeStamp.jpg"
        mediaFile = File(mediaStorageDir.path + File.separator + mImageName)
        return mediaFile
    }

}