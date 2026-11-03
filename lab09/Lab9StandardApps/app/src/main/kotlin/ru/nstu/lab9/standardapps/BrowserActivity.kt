package ru.nstu.lab9.standardapps

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class BrowserActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var addressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        webView = findViewById(R.id.webView)
        addressText = findViewById(R.id.addressText)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest,
            ): Boolean = false

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                addressText.text = url
            }

            override fun onPageFinished(view: WebView, url: String) {
                addressText.text = url
                updateNavigationButtons()
            }
        }

        findViewById<View>(R.id.browserBackButton).setOnClickListener {
            if (webView.canGoBack()) webView.goBack()
        }
        findViewById<View>(R.id.browserForwardButton).setOnClickListener {
            if (webView.canGoForward()) webView.goForward()
        }
        findViewById<View>(R.id.browserCloseButton).setOnClickListener {
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        val initialUrl = intent.dataString ?: getString(R.string.example_link_value)
        if (initialUrl.startsWith("http://") || initialUrl.startsWith("https://")) {
            webView.loadUrl(initialUrl)
        } else {
            Toast.makeText(this, R.string.page_load_error, Toast.LENGTH_LONG).show()
            finish()
        }
        updateNavigationButtons()
    }

    private fun updateNavigationButtons() {
        findViewById<View>(R.id.browserBackButton).isEnabled = webView.canGoBack()
        findViewById<View>(R.id.browserForwardButton).isEnabled = webView.canGoForward()
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}
