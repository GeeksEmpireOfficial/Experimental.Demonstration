package net.geeksempire.experimental.demonstration.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.MagazineCoverViewBinding

class MagazineCoverTemplate : AppCompatActivity() {

    private lateinit var magazineCoverViewBinding: MagazineCoverViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        magazineCoverViewBinding = MagazineCoverViewBinding.inflate(layoutInflater)
        setContentView(magazineCoverViewBinding.root)

        window.statusBarColor = getColor(android.R.color.black)
        window.navigationBarColor = getColor(android.R.color.black)


    }

}