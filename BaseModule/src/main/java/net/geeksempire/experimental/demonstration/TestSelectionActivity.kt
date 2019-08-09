package net.geeksempire.experimental.demonstration

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.tests_selection_view.*
import net.geeksempire.experimental.demonstration.GooglePayProcess.InitializeGooglePay


class TestSelectionActivity : BaseConfigurations() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tests_selection_view)
        FirebaseApp.initializeApp(applicationContext)

        requestPermissions(
            arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 123
        )

        googlePay.setOnClickListener {
            startActivity(Intent(applicationContext, InitializeGooglePay::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}