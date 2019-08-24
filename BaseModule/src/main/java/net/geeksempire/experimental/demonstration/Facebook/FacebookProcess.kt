package net.geeksempire.experimental.demonstration.Facebook

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.login.widget.ProfilePictureView
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import net.geeksempire.experimental.demonstration.R
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClassDebug

class FacebookProcess : Activity() {

    private lateinit var firebaseAuth: FirebaseAuth

    lateinit var callbackManager: CallbackManager

    lateinit var profileFacebook: Profile

    lateinit var profilePicture: ProfilePictureView

    private val facebookPermissionsProfile = "public_profile"
    private val facebookPermissionsEmail = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.facebook_view)

        val loginButton = findViewById<LoginButton>(R.id.loginFacebook) as LoginButton
        profilePicture = findViewById<ProfilePictureView>(R.id.profilePicture) as ProfilePictureView

        callbackManager = CallbackManager.Factory.create()

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (isLoggedIn) {
            profileFacebook = Profile.getCurrentProfile()
            profilePicture.profileId = profileFacebook.id
        }

        loginButton.setPermissions(listOf(facebookPermissionsEmail, facebookPermissionsProfile))
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                FunctionsClassDebug.PrintDebug("*** ${result} ***")

                if (isLoggedIn) {
                    profileFacebook = Profile.getCurrentProfile()
                    profilePicture.profileId = profileFacebook.id
                }
            }

            override fun onCancel() {
                FunctionsClassDebug.PrintDebug("*** Cancel ***")
            }

            override fun onError(error: FacebookException?) {
                FunctionsClassDebug.PrintDebug("*** ${error} ***")

            }
        })

        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    FunctionsClassDebug.PrintDebug("*** ${result} ***")

                    if (isLoggedIn) {
                        profileFacebook = Profile.getCurrentProfile()
                        profilePicture.profileId = profileFacebook.id
                    }
                }

                override fun onCancel() {
                    FunctionsClassDebug.PrintDebug("*** Cancel ***")
                }

                override fun onError(error: FacebookException?) {
                    FunctionsClassDebug.PrintDebug("*** ${error} ***")

                }

            })

        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (isLoggedIn) {
            profileFacebook = Profile.getCurrentProfile()
            profilePicture.profileId = profileFacebook.id

            val credential = FacebookAuthProvider.getCredential(accessToken.token)
            firebaseAuth.currentUser!!.linkWithCredential(credential).addOnSuccessListener {
                FunctionsClassDebug.PrintDebug("*** ${it.additionalUserInfo} ***")

            }.addOnFailureListener {
                FunctionsClassDebug.PrintDebug("*** ${it} ***")

            }
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }
}