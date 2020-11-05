package net.geeksempire.experimental.demonstration

import android.Manifest
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.tests_selection_view.*
import net.geeksempire.experimental.demonstration.DynamicFeatures.InAppUpdate
import net.geeksempire.experimental.demonstration.Facebook.FacebookProcess
import net.geeksempire.experimental.demonstration.GooglePayProcess.InitializeGooglePay
import net.geeksempire.experimental.demonstration.HomeLauncher.HomeLauncherMainView
import net.geeksempire.experimental.demonstration.Network.HttpsConnectionDemonstration
import net.geeksempire.experimental.demonstration.PayPalProcess.InitializePayPal
import net.geeksempire.experimental.demonstration.Process.WorkBackgroundProcess
import net.geeksempire.experimental.demonstration.UI.MagazineCoverTemplate
import net.geeksempire.experimental.demonstration.UI.MaterialUI
import net.geeksempire.experimental.demonstration.Utils.Functions.FunctionsClass

class TestSelectionActivity : BaseConfigurations() {

    lateinit var functionsClass: FunctionsClass

    lateinit var firebaseAuth: FirebaseAuth
    var firebaseUser: FirebaseUser? = null

    private val GOOGLE_SIGN_IN: Int = 666

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tests_selection_view)
        FirebaseApp.initializeApp(applicationContext)

        //Runtime Permission
        requestPermissions(
            arrayOf (
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

        httpsConnection.setOnClickListener {
            startActivity(Intent(applicationContext, HttpsConnectionDemonstration::class.java))
        }

        backgroundProcess.setOnClickListener {

            val workRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkBackgroundProcess>().build()

            WorkManager
                .getInstance(applicationContext)
                .enqueue(workRequest)

            WorkManager.getInstance(applicationContext).getWorkInfoByIdLiveData(workRequest.id)
                .observe(this@TestSelectionActivity, Observer { workInfo ->


                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {

                        val iconBitmap = workInfo.outputData.getByteArray("KEY_IMAGE_DATA")!!

                        println(">>> ${iconBitmap.size}")

                        backgroundProcess.icon = BitmapDrawable(resources, BitmapFactory.decodeByteArray(iconBitmap, 0, iconBitmap.size))

                    }

                })

        }

        homeLauncher.setOnClickListener {
            startActivity(Intent(applicationContext, HomeLauncherMainView::class.java))
        }

        coverMagazine.setOnClickListener {
            startActivity(Intent(applicationContext, MagazineCoverTemplate::class.java))
        }

        coverMagazine.setOnLongClickListener {

            Intent(applicationContext, MagazineCoverTemplate::class.java).apply {

                startActivity(this@apply)
            }

            false
        }

        createDynamicLink.setOnClickListener {

            firebaseUser?.let { firebaseUser ->

                val dynamicLink = Firebase.dynamicLinks.dynamicLink {
                    link = Uri.parse("https://www.demonstration.net/")
                        .buildUpon()
                        .appendQueryParameter("UID", firebaseUser.uid).build()
                    domainUriPrefix = "https://demonstration.page.link"

                    socialMetaTagParameters {

                        title = firebaseUser.uid

                    }

                    androidParameters(packageName) {

                    }

                    iosParameters(packageName) {

                    }

                }

                val dynamicLinkUri = dynamicLink.uri


                println("*****1 ${firebaseUser.uid} *****")
                println("*****1 ${dynamicLinkUri} *****")

                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(dynamicLinkUri.toString())))

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