package ru.nstu.lab9.standardapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<android.view.View>(R.id.networkButton).setOnClickListener {
            startActivity(Intent(this, NetworkActivity::class.java))
        }
        findViewById<android.view.View>(R.id.phoneButton).setOnClickListener {
            startActivity(Intent(this, PhoneActivity::class.java))
        }
        findViewById<android.view.View>(R.id.emailButton).setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
        findViewById<android.view.View>(R.id.linksButton).setOnClickListener {
            startActivity(Intent(this, LinksActivity::class.java))
        }
    }
}
