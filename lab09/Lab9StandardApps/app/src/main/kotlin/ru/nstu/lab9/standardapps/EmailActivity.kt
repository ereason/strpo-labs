package ru.nstu.lab9.standardapps

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class EmailActivity : AppCompatActivity() {
    private lateinit var recipientInput: TextInputEditText
    private lateinit var subjectInput: TextInputEditText
    private lateinit var messageInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        recipientInput = findViewById(R.id.recipientInput)
        subjectInput = findViewById(R.id.subjectInput)
        messageInput = findViewById(R.id.messageInput)

        findViewById<android.view.View>(R.id.clearEmailButton).setOnClickListener {
            clearForm()
        }
        findViewById<android.view.View>(R.id.sendEmailButton).setOnClickListener {
            openEmailApp()
        }
        findViewById<android.view.View>(R.id.emailBackButton).setOnClickListener {
            finish()
        }
    }

    private fun clearForm() {
        recipientInput.text?.clear()
        subjectInput.text?.clear()
        messageInput.text?.clear()
        recipientInput.requestFocus()
        Toast.makeText(this, R.string.form_cleared, Toast.LENGTH_SHORT).show()
    }

    private fun openEmailApp() {
        val recipient = recipientInput.text.toString().trim()
        val subject = subjectInput.text.toString().trim()
        val message = messageInput.text.toString().trim()

        if (recipient.isBlank() || subject.isBlank() || message.isBlank()) {
            Toast.makeText(this, R.string.fill_email_fields, Toast.LENGTH_SHORT).show()
            return
        }

        val mailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(
                "mailto:${Uri.encode(recipient, "@")}?subject=${Uri.encode(subject)}" +
                    "&body=${Uri.encode(message)}",
            )
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }
        if (mailIntent.resolveActivity(packageManager) == null) {
            Toast.makeText(this, R.string.email_app_not_found, Toast.LENGTH_LONG).show()
            return
        }

        try {
            startActivity(Intent.createChooser(mailIntent, getString(R.string.choose_email_app)))
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, R.string.email_app_not_found, Toast.LENGTH_LONG).show()
        }
    }
}
