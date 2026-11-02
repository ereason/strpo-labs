package ru.nstu.lab7.kotlinapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var resultText: TextView
    private lateinit var eventText: TextView
    private var popupItemChecked = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        resultText = findViewById(R.id.resultText)
        eventText = findViewById(R.id.eventText)

        findViewById<View>(R.id.popupButton).setOnClickListener(::showPopupMenu)
        findViewById<View>(R.id.popupText).setOnClickListener(::showPopupMenu)
        findViewById<View>(R.id.swiftImage).setOnClickListener(::showPopupMenu)
        findViewById<View>(R.id.dynamicButton).setOnClickListener(::showProgrammaticMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_settings -> {
            resultText.setText(R.string.selected_settings)
            true
        }

        R.id.action_java, R.id.action_kotlin, R.id.action_python -> {
            item.isChecked = true
            resultText.text = getString(R.string.selected_language, item.title)
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    private fun showPopupMenu(anchor: View) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.menu.findItem(R.id.popup_checked_item).isChecked = popupItemChecked
        popupMenu.setOnMenuItemClickListener(::handlePopupItem)
        popupMenu.setOnDismissListener {
            eventText.setText(R.string.popup_closed)
        }
        popupMenu.show()
        eventText.setText(R.string.popup_opened)
    }

    private fun handlePopupItem(item: MenuItem): Boolean {
        if (item.itemId == R.id.popup_checked_item) {
            popupItemChecked = !item.isChecked
        }
        return when (item.itemId) {
            R.id.popup_one,
            R.id.popup_two,
            R.id.popup_submenu_item,
            R.id.popup_checked_item,
            R.id.popup_six -> {
                resultText.text = getString(R.string.selected_popup, item.title)
                true
            }

            else -> false
        }
    }

    private fun showProgrammaticMenu(anchor: View) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menu.add(
            R.id.dynamic_group,
            R.id.dynamic_first,
            1,
            R.string.dynamic_first,
        )
        popupMenu.menu.add(
            R.id.dynamic_group,
            R.id.dynamic_second,
            2,
            R.string.dynamic_second,
        )
        popupMenu.setOnMenuItemClickListener { item ->
            resultText.text = getString(R.string.dynamic_selected, item.title)
            true
        }
        popupMenu.setOnDismissListener {
            eventText.setText(R.string.popup_closed)
        }
        popupMenu.show()
        eventText.setText(R.string.popup_opened)
    }
}
