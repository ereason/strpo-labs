package ru.nstu.lab9.standardapps

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LinksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_links)

        findViewById<android.view.View>(R.id.openLinkButton).setOnClickListener {
            openSelectedLink()
        }
        findViewById<android.view.View>(R.id.linksBackButton).setOnClickListener {
            finish()
        }
    }

    private fun openSelectedLink() {
        val url = when (findViewById<RadioGroup>(R.id.webLinks).checkedRadioButtonId) {
            R.id.exampleLink -> getString(R.string.example_link_value)
            R.id.nstuLink -> getString(R.string.nstu_link_value)
            R.id.kotlinLink -> getString(R.string.kotlin_link_value)
            else -> null
        }

        if (url == null) {
            Toast.makeText(this, R.string.choose_web_link, Toast.LENGTH_SHORT).show()
            return
        }

        val uri = Uri.parse(url)
        val viewIntent = Intent(Intent.ACTION_VIEW, uri)
        val internalBrowserIntent = Intent(this, BrowserActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            data = uri
        }
        val chooserIntent = Intent.createChooser(viewIntent, getString(R.string.choose_browser)).apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(internalBrowserIntent))
        }
        try {
            startActivity(chooserIntent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, R.string.browser_not_found, Toast.LENGTH_LONG).show()
        }
    }
}
