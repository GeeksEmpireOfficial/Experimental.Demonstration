package net.geeksempire.experimental.demonstration.PhysicsAnimation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.geeksempire.experimental.demonstration.databinding.DifferentMotionsViewBinding

class DifferentMotions : AppCompatActivity() {

    private lateinit var differentMotionsViewBinding: DifferentMotionsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        differentMotionsViewBinding = DifferentMotionsViewBinding.inflate(layoutInflater)
        setContentView(differentMotionsViewBinding.root)
    }

}