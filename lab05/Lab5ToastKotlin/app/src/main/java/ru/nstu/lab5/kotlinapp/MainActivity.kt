package ru.nstu.lab5.kotlinapp

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textToastButton: Button = findViewById(R.id.textToastButton)
        textToastButton.setOnClickListener {
            Toast.makeText(
                applicationContext,
                R.string.kotlin_toast_message,
                Toast.LENGTH_SHORT,
            ).show()
        }

        val imageToastButton: Button = findViewById(R.id.imageToastButton)
        imageToastButton.setOnClickListener {
            showImageToast()
        }
    }

    @Suppress("DEPRECATION")
    private fun showImageToast() {
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(20), dp(16), dp(20), dp(16))
            background = GradientDrawable().apply {
                setColor(getColor(R.color.lab_primary_dark))
                cornerRadius = dp(24).toFloat()
            }
        }

        val image = ImageView(this).apply {
            setImageResource(R.drawable.ic_swift)
        }
        content.addView(image, LinearLayout.LayoutParams(dp(72), dp(72)))

        val message = TextView(this).apply {
            setText(R.string.swift_toast_message)
            setTextColor(getColor(R.color.white))
            textSize = 16f
        }
        val messageParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
        ).apply {
            topMargin = dp(8)
        }
        content.addView(message, messageParams)

        Toast(applicationContext).apply {
            duration = Toast.LENGTH_LONG
            setGravity(Gravity.BOTTOM, 0, dp(96))
            view = content
            show()
        }
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}
