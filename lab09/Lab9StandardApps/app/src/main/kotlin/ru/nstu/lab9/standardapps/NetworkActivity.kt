package ru.nstu.lab9.standardapps

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        val networkImage = findViewById<ImageButton>(R.id.networkImage)
        networkImage.setOnClickListener {
            showNetworkState(networkImage, true)
        }
        findViewById<android.view.View>(R.id.backButton).setOnClickListener {
            finish()
        }

        showNetworkState(networkImage, false)
    }

    private fun showNetworkState(networkImage: ImageButton, showToast: Boolean) {
        val connected = isInternetAvailable()
        val message = if (connected) {
            networkImage.setImageResource(R.drawable.ic_wifi_online)
            getString(R.string.network_online)
        } else {
            networkImage.setImageResource(R.drawable.ic_wifi_offline)
            getString(R.string.network_offline)
        }

        findViewById<TextView>(R.id.networkStatus).text = message
        if (showToast) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun isInternetAvailable(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = manager.activeNetwork ?: return false
        val capabilities = manager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}
