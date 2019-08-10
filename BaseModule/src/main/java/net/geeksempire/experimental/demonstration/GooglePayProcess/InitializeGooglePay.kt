package net.geeksempire.experimental.demonstration.GooglePayProcess

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.wallet.*
import kotlinx.android.synthetic.main.google_pay_view.*
import net.geeksempire.experimental.demonstration.R
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClassDebug
import net.geekstools.floatshort.PRO.Util.Functions.FunctionsClassSecurity
import org.json.JSONArray
import org.json.JSONObject

class InitializeGooglePay : Activity() {

    lateinit var paymentsClient: PaymentsClient

    private val LOAD_PAYMENT_DATA_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.google_pay_view)

        val walletOptions = Wallet.WalletOptions.Builder()
            .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
            .build()

        paymentsClient = Wallet.getPaymentsClient(this@InitializeGooglePay, walletOptions)
        val paymentDataRequest = IsReadyToPayRequest.fromJson(getIsReadyToPayRequest().toString())
        val taskIsReadyToPayRequest = paymentsClient.isReadyToPay(paymentDataRequest)
        taskIsReadyToPayRequest.addOnCompleteListener {
            try {
                FunctionsClassDebug.PrintDebug("*** ${it.result} ***")
                taskIsReadyToPayRequest.getResult(ApiException::class.java)
                    ?.let(::setGooglePayAvailable)

                val result: Boolean = taskIsReadyToPayRequest.getResult(ApiException::class.java)!!
                if (result) {
                    // show Google Pay as a payment option
                    googlePay.visibility = View.VISIBLE
                    googlePay.setOnClickListener {
                        val request = PaymentDataRequest.fromJson(paymentDataRequest.toString())
                        if (request != null) {
                            AutoResolveHelper.resolveTask(
                                paymentsClient.loadPaymentData(request),
                                this@InitializeGooglePay,
                                LOAD_PAYMENT_DATA_REQUEST_CODE
                            )
                        }

                    }
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }.addOnSuccessListener {
            FunctionsClassDebug.PrintDebug("*** Success | ${it} ***")

        }.addOnFailureListener {
            FunctionsClassDebug.PrintDebug("*** Fail | ${it} ***")

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val paymentData: PaymentData? = PaymentData.getFromIntent(data!!)
                        val json = paymentData!!.toJson();
                        // if using gateway tokenization, pass this token without modification
                        val paymentMethodData = JSONObject(json).getJSONObject("paymentMethodData")
                        val paymentToken = paymentMethodData
                            .getJSONObject("tokenizationData")
                            .getString("token");
                    }

                    Activity.RESULT_CANCELED -> {

                    }
                    AutoResolveHelper.RESULT_ERROR -> {
                        val status = AutoResolveHelper.getStatusFromIntent(data)

                    }
                }
            }
        }
    }

    private fun getBaseRequest(): JSONObject {
        return JSONObject()
            .put("apiVersion", 2)
            .put("apiVersionMinor", 0)
    }

    private fun getTokenizationSpecification(): JSONObject {
        val tokenizationSpecification = JSONObject()
        tokenizationSpecification.put("type", "DIRECT")
        tokenizationSpecification.put(
            "parameters",
            JSONObject()
                .put("protocolVersion", "ECv2")
                .put(
                    "publicKey",
                    FunctionsClassSecurity(applicationContext).encodeStringBase64(packageName)
                    /*"BOdoXP1aiNp.....kh3JUhiSZKHYF2Y="*/
                )
        )

        return tokenizationSpecification
    }

    /*private fun getTokenizationSpecification(): JSONObject {
        val tokenizationSpecification = JSONObject()
        tokenizationSpecification.put("type", "PAYMENT_GATEWAY")
        tokenizationSpecification.put(
            "parameters",
            JSONObject()
                .put("gateway", "example")
                .put("gatewayMerchantId", "exampleMerchantId")
        )

        return tokenizationSpecification
    }*/

    private fun getAllowedCardNetworks(): JSONArray {
        return JSONArray()
            .put("AMEX")
            .put("DISCOVER")
            .put("INTERAC")
            .put("JCB")
            .put("MASTERCARD")
            .put("VISA")
    }

    private fun getAllowedCardAuthMethods(): JSONArray {
        return JSONArray()
            .put("PAN_ONLY")
            .put("CRYPTOGRAM_3DS")
    }

    private fun getBaseCardPaymentMethod(): JSONObject {
        val cardPaymentMethod = JSONObject()
        cardPaymentMethod.put("type", "CARD")
        cardPaymentMethod.put(
            "parameters",
            JSONObject()
                .put("allowedAuthMethods", getAllowedCardAuthMethods())
                .put("allowedCardNetworks", getAllowedCardNetworks())
        )

        return cardPaymentMethod
    }

    private fun getCardPaymentMethod(): JSONObject {
        val cardPaymentMethod = getBaseCardPaymentMethod()
        cardPaymentMethod.put("tokenizationSpecification", getTokenizationSpecification())

        return cardPaymentMethod
    }

    fun getIsReadyToPayRequest(): JSONObject {
        val isReadyToPayRequest = getBaseRequest()
        isReadyToPayRequest.put(
            "allowedPaymentMethods",
            JSONArray()
                .put(getBaseCardPaymentMethod())
        )

        return isReadyToPayRequest
    }

    private fun getTransactionInfo(): JSONObject {
        val transactionInfo = JSONObject()
        transactionInfo.put("totalPrice", "12.34")
        transactionInfo.put("totalPriceStatus", "FINAL")
        transactionInfo.put("currencyCode", "USD")

        return transactionInfo
    }

    private fun getMerchantInfo(): JSONObject {
        return JSONObject()
            .put("merchantName", "Geeks Empire Inc.")
    }

    fun getPaymentDataRequest(): JSONObject {
        val paymentDataRequest = getBaseRequest()
        paymentDataRequest.put(
            "allowedPaymentMethods",
            JSONArray()
                .put(getCardPaymentMethod())
        )
        paymentDataRequest.put("transactionInfo", getTransactionInfo())
        paymentDataRequest.put("merchantInfo", getMerchantInfo())

        return paymentDataRequest
    }

    private fun setGooglePayAvailable(available: Boolean) {
        if (available) {
            googlePay.visibility = View.VISIBLE
        } else {
            Toast.makeText(
                this,
                "Unfortunately, Google Pay is not available on this device",
                Toast.LENGTH_LONG
            ).show();
        }
    }
}