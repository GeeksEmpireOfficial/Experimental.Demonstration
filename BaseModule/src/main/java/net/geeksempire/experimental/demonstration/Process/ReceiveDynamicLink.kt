package net.geeksempire.experimental.demonstration.Process

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class ReceiveDynamicLink : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this@ReceiveDynamicLink) { pendingDynamicLinkData ->

                var dynamicLinkUri: Uri? = null

                if (pendingDynamicLinkData != null) {
                    dynamicLinkUri = pendingDynamicLinkData.link
                }

                println("*****2 ${dynamicLinkUri} *****")
                println("*****2 ${pendingDynamicLinkData} *****")

            }
            .addOnFailureListener(this) { exception ->
                exception.printStackTrace()

            }

    }

}