package com.example.birthdayscheduler


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import org.http4k.core.*
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.json.JSONObject
import android.content.pm.PackageManager
import android.Manifest
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.TextView
import android.net.wifi.WifiManager
import java.util.Formatter






class MainActivity : AppCompatActivity() {

    private val INTERNET_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.SEND_SMS), 123)
        }
        requestInternetPermission()
        val ipAddress: String = getLocalIpAddress()
        val textView = findViewById<TextView>(R.id.ip_address)
        textView.text = ipAddress
    }

    private fun requestInternetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.INTERNET), INTERNET_PERMISSION_CODE)
        } else {
            startServer()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            INTERNET_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startServer()
                }
            }
        }
    }

    private fun startServer() {
        val app: HttpHandler = { request ->
            val jsonString = request.bodyString()
            val json = JSONObject(jsonString)
            val authToken = json.getString("Authorization")
            // val authToken = request.header("Authorization")
            when (request.method) {
                POST -> {
                    // This is some basic security. You should change your AUTH TOKEN here
                    val validAuthToken = "123456789"
                    if (authToken != null && authToken == validAuthToken) {
                        val message = json.getString("message")
                        val number = json.getString("number")
                        sendSMS(number, message)
                        Response(OK).body("SMS sent!")
                    } else {
                        Response(Status.UNAUTHORIZED).body(authToken.toString())
                    }
                }
                else -> Response(OK).body("Failed to receive POST")
            }
        }

        // this is the port the API will be running on
        val server = app.asServer(Jetty(9000))
        server.start()
    }

    // sends an SMS
    private fun sendSMS(phoneNumber: String, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }

    // used to display the users IP address on the main page
    private fun getLocalIpAddress(): String {
        val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val ip = wifiInfo.ipAddress
        return String.format("%d.%d.%d.%d", ip and 0xff, ip shr 8 and 0xff, ip shr 16 and 0xff, ip shr 24 and 0xff)
    }


}