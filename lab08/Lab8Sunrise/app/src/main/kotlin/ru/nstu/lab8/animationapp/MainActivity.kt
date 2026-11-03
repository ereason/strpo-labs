package ru.nstu.lab8.animationapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sun = findViewById<ImageView>(R.id.sun)
        sun.startAnimation(AnimationUtils.loadAnimation(this, R.anim.sun_rise))

        val minuteHand = findViewById<ImageView>(R.id.minuteHand)
        minuteHand.startAnimation(AnimationUtils.loadAnimation(this, R.anim.minute_turn))

        val hourHand = findViewById<ImageView>(R.id.hourHand)
        hourHand.startAnimation(AnimationUtils.loadAnimation(this, R.anim.hour_turn))
    }
}
