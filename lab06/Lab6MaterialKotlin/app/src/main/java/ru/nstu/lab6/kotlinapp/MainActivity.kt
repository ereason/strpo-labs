package ru.nstu.lab6.kotlinapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var activeSnackbar: Snackbar? = null
    private lateinit var eventText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        eventText = findViewById(R.id.eventText)

        findViewById<View>(R.id.basicButton).setOnClickListener { view ->
            showTracked(Snackbar.make(view, R.string.basic_message, Snackbar.LENGTH_LONG))
        }

        findViewById<View>(R.id.durationButton).setOnClickListener { view ->
            showTracked(Snackbar.make(view, R.string.duration_message, 8000))
        }

        findViewById<View>(R.id.indefiniteButton).setOnClickListener { view ->
            showTracked(Snackbar.make(view, R.string.indefinite_message, Snackbar.LENGTH_INDEFINITE))
        }

        findViewById<View>(R.id.dismissButton).setOnClickListener {
            activeSnackbar?.dismiss()
        }

        findViewById<View>(R.id.colorButton).setOnClickListener(::showColoredSnackbar)
        findViewById<View>(R.id.roundedButton).setOnClickListener(::showRoundedSnackbar)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(::showActionSnackbar)
    }

    private fun showColoredSnackbar(view: View) {
        val snackbar = Snackbar.make(view, R.string.colored_message, Snackbar.LENGTH_INDEFINITE)
            .setBackgroundTint(getColor(R.color.green_primary))
            .setTextColor(getColor(R.color.snackbar_text))
            .setActionTextColor(getColor(R.color.amber_accent))
            .setAction(R.string.dismiss_action) {
                updateEvent("нажата кнопка закрытия")
            }
        showTracked(snackbar)
    }

    private fun showActionSnackbar(view: View) {
        val snackbar = Snackbar.make(view, R.string.action_message, Snackbar.LENGTH_INDEFINITE)
            .setBackgroundTint(getColor(R.color.green_dark))
            .setTextColor(getColor(R.color.snackbar_text))
            .setActionTextColor(getColor(R.color.amber_accent))
            .setAction(R.string.action_yes) {
                Toast.makeText(applicationContext, R.string.action_result, Toast.LENGTH_SHORT).show()
            }
        showTracked(snackbar)
    }

    private fun showRoundedSnackbar(view: View) {
        val snackbar = Snackbar.make(view, R.string.rounded_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.dismiss_action) {
                updateEvent("закругленная панель закрыта")
            }
        SnackbarHelper.configure(this, snackbar)
        showTracked(snackbar)
    }

    private fun showTracked(snackbar: Snackbar) {
        activeSnackbar = snackbar
        snackbar.addCallback(object : Snackbar.Callback() {
            override fun onShown(shownSnackbar: Snackbar) {
                updateEvent("Snackbar показан")
            }

            override fun onDismissed(dismissedSnackbar: Snackbar, event: Int) {
                updateEvent(dismissReason(event))
                if (activeSnackbar === dismissedSnackbar) {
                    activeSnackbar = null
                }
            }
        })
        snackbar.show()
    }

    private fun dismissReason(event: Int): String = when (event) {
        Snackbar.Callback.DISMISS_EVENT_SWIPE -> "закрыт смахиванием"
        Snackbar.Callback.DISMISS_EVENT_ACTION -> "закрыт действием"
        Snackbar.Callback.DISMISS_EVENT_TIMEOUT -> "закрыт по тайм-ауту"
        Snackbar.Callback.DISMISS_EVENT_MANUAL -> "закрыт вручную"
        Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE -> "заменен новым Snackbar"
        else -> "закрыт"
    }

    private fun updateEvent(event: String) {
        Log.i(TAG, event)
        eventText.text = getString(R.string.event_format, event)
    }

    private companion object {
        const val TAG = "Lab6Snackbar"
    }
}
