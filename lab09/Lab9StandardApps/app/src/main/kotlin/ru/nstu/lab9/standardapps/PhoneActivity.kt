package ru.nstu.lab9.standardapps

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone)

        findViewById<android.view.View>(R.id.openDialerButton).setOnClickListener {
            openSelectedNumber()
        }
        findViewById<android.view.View>(R.id.phoneBackButton).setOnClickListener {
            finish()
        }
    }

    private fun openSelectedNumber() {
        val selectedNumber = when (findViewById<RadioGroup>(R.id.phoneNumbers).checkedRadioButtonId) {
            R.id.firstNumber -> getString(R.string.first_phone_value)
            R.id.secondNumber -> getString(R.string.second_phone_value)
            R.id.thirdNumber -> getString(R.string.third_phone_value)
            else -> null
        }

        if (selectedNumber == null) {
            Toast.makeText(this, R.string.choose_phone_number, Toast.LENGTH_SHORT).show()
            return
        }

        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$selectedNumber"))
        try {
            startActivity(dialIntent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(this, R.string.dialer_not_found, Toast.LENGTH_LONG).show()
        }
    }
}
