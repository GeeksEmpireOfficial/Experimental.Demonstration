package net.geeksempire.experimental.demonstration

import android.app.Activity
import android.app.assist.AssistContent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.appindexing.Action
import com.google.firebase.appindexing.FirebaseUserActions
import com.google.firebase.appindexing.builders.AssistActionBuilder
import org.json.JSONObject

class AssistantAction : Activity() {

    private val actionTokenExtra = "actions.fulfillment.extra.ACTION_TOKEN"

    var AppNameToLaunch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val actionIntent = intent.action
        val actionData = intent.data

        AppNameToLaunch =
            actionData.toString().replace("https://geeksempire.net/DemoAssistantActions/AppToLaunch=?AppName=", "")

        Toast.makeText(applicationContext, AppNameToLaunch, Toast.LENGTH_LONG).show()

        notifyActionStatus(Action.Builder.STATUS_TYPE_COMPLETED)
    }

    override fun onProvideAssistContent(outContent: AssistContent) {
        super.onProvideAssistContent(outContent)

        // JSON-LD object based on Schema.org structured data
        outContent.structuredData = JSONObject()
            .put("@type", "feature")
            .put("name", AppNameToLaunch)
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