package net.geeksempire.experimental.demonstration

import android.app.Activity
import android.app.assist.AssistContent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.appindexing.FirebaseUserActions
import com.google.firebase.appindexing.builders.AssistActionBuilder
import org.json.JSONObject

class AssistantAction : Activity() {

    private val actionTokenExtra = "actions.fulfillment.extra.ACTION_TOKEN"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //APP_ACTION
        val actionIntent = intent
        val appLinkAction = actionIntent.action
        val appLinkData = actionIntent.data

        Toast.makeText(
            applicationContext,
            appLinkAction + "\n"
                    + intent.getStringExtra("feature") + "\n"
                    + appLinkData, Toast.LENGTH_LONG
        ).show()
    }

    override fun onProvideAssistContent(outContent: AssistContent) {
        super.onProvideAssistContent(outContent)

        // JSON-LD object based on Schema.org structured data
        outContent.structuredData = JSONObject()
            .put("@type", "TYPE")
            .put("name", "ACTION NAME")
            .put("url", "https://geeksempire.net/DemoAssistantActions")
            .toString()
    }

    // On Action success
    //notifyActionStatus(Action.Builder.STATUS_TYPE_COMPLETED)

    // On Action failed
    //notifyActionStatus(Action.Builder.STATUS_TYPE_FAILED)

    fun notifyActionStatus(actionStatus: String) {
        val actionToken = intent.getStringExtra(actionTokenExtra)
        val action = AssistActionBuilder()
            .setActionToken(actionToken)
            .setActionStatus(actionStatus)
            .build()
        FirebaseUserActions.getInstance().end(action)
    }
}