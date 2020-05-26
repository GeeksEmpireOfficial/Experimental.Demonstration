package net.geeksempire.experimental.demonstration.Network

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpsConnectionDemonstration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {

            performNetworkOperation()
        }

    }

    private suspend fun performNetworkOperation() {

        withContext(SupervisorJob() + Dispatchers.IO) {

            val url = URL("https://api.giphy.com/v1/gifs/search?api_key=uvO9uLD5yDRHZ1NgI6zOroJKLsJbvNDQ&q=Google&limit=1&offset=0&rating=G&lang=en")
            val urlConnectionHttps: HttpsURLConnection = url.openConnection() as HttpsURLConnection
            urlConnectionHttps.requestMethod = "GET"
            urlConnectionHttps.connect()

            if (urlConnectionHttps.responseCode == HttpsURLConnection.HTTP_OK) {

                try {
                    val inputStream = /*BufferedInputStream*/(urlConnectionHttps.inputStream)

                    val inputStreamReader = inputStream.reader()
                    val jsonObject = JSONObject(inputStreamReader.readText())
                    val jsonArray = jsonObject.getJSONArray("data")
                    val itemJsonObject = jsonArray.getJSONObject(0)
                    val gifLink = itemJsonObject.getString("url")

                    println("*** ${gifLink} ***")

                } finally {

                    urlConnectionHttps.disconnect()
                }

            } else {

                urlConnectionHttps.disconnect()
            }
        }
    }
}