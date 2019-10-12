package net.geeksempire.experimental.demonstration

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.tests_selection_view.*
import net.geeksempire.experimental.demonstration.DynamicFeatures.InAppUpdate
import net.geeksempire.experimental.demonstration.Facebook.FacebookProcess
import net.geeksempire.experimental.demonstration.GooglePayProcess.InitializeGooglePay
import net.geeksempire.experimental.demonstration.PayPalProcess.InitializePayPal
import net.geeksempire.experimental.demonstration.UI.MaterialUI
import net.geeksempire.experimental.demonstration.Utils.Functions.FunctionsClass
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClassDebug
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class TestSelectionActivity : BaseConfigurations() {

    lateinit var functionsClass: FunctionsClass

    lateinit var firebaseAuth: FirebaseAuth
    var firebaseUser: FirebaseUser? = null

    private val GOOGLE_SIGN_IN = 666

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

        functionsClass = FunctionsClass(applicationContext)

        googlePay.setOnClickListener {
            startActivity(Intent(applicationContext, InitializeGooglePay::class.java))
        }

        brainTreePayPal.setOnClickListener {
            startActivity(Intent(applicationContext, InitializePayPal::class.java))
        }

        inAppUpdate.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    InAppUpdate/*DynamicFeaturesUpdateActivity*/::class.java
                )
            )
        }

        facebookAPI.setOnClickListener {
            startActivity(Intent(applicationContext, FacebookProcess::class.java))
        }

        materialUITest.setOnClickListener {
            startActivity(Intent(applicationContext, MaterialUI::class.java))
        }
        if (BuildConfig.DEBUG) {
            try {
                val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
                for (signature in info.signatures) {
                    val messageDigest = MessageDigest.getInstance("SHA")
                    messageDigest.update(signature.toByteArray())
                    FunctionsClassDebug.PrintDebug(
                        "KeyHash: ${Base64.encodeToString(
                            messageDigest.digest(),
                            Base64.DEFAULT
                        )} "
                    )
                }
            } catch (e: PackageManager.NameNotFoundException) {

            } catch (e: NoSuchAlgorithmException) {

            }

        }

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser
    }

    override fun onStart() {
        super.onStart()

        if (firebaseUser == null) {
            val googleSignInOptions =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.webClientId))
                    .requestEmail()
                    .build()

            val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
            try {
                googleSignInClient.signOut()
                googleSignInClient.revokeAccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
        } else {
            googlePay.visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        val homeScreen = Intent(Intent.ACTION_MAIN)
        homeScreen.addCategory(Intent.CATEGORY_HOME)
        homeScreen.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(
            homeScreen,
            ActivityOptions.makeCustomAnimation(
                applicationContext,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            ).toBundle()
        )
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            try {
                val googleSignInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
                val googleSignInAccount =
                    googleSignInAccountTask.getResult(ApiException::class.java)

                val authCredential =
                    GoogleAuthProvider.getCredential(googleSignInAccount!!.getIdToken(), null)
                firebaseAuth.signInWithCredential(authCredential)
                    .addOnSuccessListener {
                        firebaseUser = firebaseAuth.currentUser!!
                        if (firebaseUser != null) {
                            googlePay.visibility = View.VISIBLE
                        }
                    }.addOnFailureListener {

                    }
            } catch (e: ApiException) {
                e.printStackTrace()
            }

        }
    }
}