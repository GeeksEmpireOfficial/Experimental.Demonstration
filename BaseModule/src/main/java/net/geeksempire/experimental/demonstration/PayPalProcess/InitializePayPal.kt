package net.geeksempire.experimental.demonstration.PayPalProcess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.braintreepayments.api.dropin.DropInActivity
import com.braintreepayments.api.dropin.DropInRequest
import com.braintreepayments.api.dropin.DropInResult
import com.google.firebase.functions.FirebaseFunctions
import kotlinx.android.synthetic.main.braintree_views.*
import net.geeksempire.experimental.demonstration.R
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClassDebug

class InitializePayPal : AppCompatActivity() {

    private lateinit var clientToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.braintree_views)

        //sandbox_7b32fxq5_4t4xz6vnm6dbk5jt
        brainTreeDropIn.setOnClickListener {
            val dropInRequest = DropInRequest().clientToken(clientToken)
            startActivityForResult(dropInRequest.getIntent(this), 123)
        }

        FirebaseFunctions.getInstance()
            .getHttpsCallable("braintreeClientToken")
            .call()
            .continueWith { task ->
                val resultToContinueWith = task.result?.data as HashMap<String, String?>
                FunctionsClassDebug.PrintDebug("*** Client Token ${resultToContinueWith}")

                resultToContinueWith["ClientTokenResponse"]!!
            }.addOnSuccessListener {
                clientToken = it
                FunctionsClassDebug.PrintDebug("*** Client Token ${clientToken}")

                brainTreeDropIn.visibility = View.VISIBLE
            }.addOnFailureListener {
                FunctionsClassDebug.PrintDebug("*** Client Token Exception ${it}")
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data!!.getParcelableExtra<DropInResult>(DropInResult.EXTRA_DROP_IN_RESULT)
                val paymentMethodNonce = result?.paymentMethodNonce!!.nonce
                FunctionsClassDebug.PrintDebug("*** ${paymentMethodNonce} ***")
                // use the result to update your UI and send the payment method nonce to your server

                val transactionDate: HashMap<String, Any?> = HashMap<String, Any?>()
                transactionDate["paymentMethodNonceFromClient"] = paymentMethodNonce

                FirebaseFunctions.getInstance()
                    .getHttpsCallable("createTransaction")
                    .call(transactionDate)
                    .continueWith { task ->
                        val resultToContinueWith = task.result?.data as HashMap<String, String?>
                        FunctionsClassDebug.PrintDebug("*** Create Transaction ${resultToContinueWith}")

                        resultToContinueWith["CreateTransactionResponse"]!!
                    }.addOnSuccessListener {
                        clientToken = it
                        FunctionsClassDebug.PrintDebug("*** Create Transaction ${clientToken}")

                    }.addOnFailureListener {
                        FunctionsClassDebug.PrintDebug("*** Create Transaction Exception ${it}")
                    }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                val error = data!!.getSerializableExtra(DropInActivity.EXTRA_ERROR) as Exception
            }
        }
    }
}