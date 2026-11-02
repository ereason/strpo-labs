package ru.nstu.lab7.javaapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private TextView eventText;
    private boolean popupItemChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultText = findViewById(R.id.resultText);
        eventText = findViewById(R.id.eventText);

        findViewById(R.id.popupButton).setOnClickListener(this::showPopupMenu);
        findViewById(R.id.popupText).setOnClickListener(this::showPopupMenu);
        findViewById(R.id.swiftImage).setOnClickListener(this::showPopupMenu);
        findViewById(R.id.dynamicButton).setOnClickListener(this::showProgrammaticMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            resultText.setText(R.string.selected_settings);
            return true;
        }
        if (id == R.id.action_java || id == R.id.action_kotlin || id == R.id.action_python) {
            item.setChecked(true);
            resultText.setText(getString(R.string.selected_language, item.getTitle()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.getMenu().findItem(R.id.popup_checked_item).setChecked(popupItemChecked);
        popupMenu.setOnMenuItemClickListener(this::handlePopupItem);
        popupMenu.setOnDismissListener(menu -> eventText.setText(R.string.popup_closed));
        popupMenu.show();
        eventText.setText(R.string.popup_opened);
    }

    private boolean handlePopupItem(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.popup_checked_item) {
            popupItemChecked = !item.isChecked();
        }
        if (id == R.id.popup_one
                || id == R.id.popup_two
                || id == R.id.popup_submenu_item
                || id == R.id.popup_checked_item
                || id == R.id.popup_six) {
            resultText.setText(getString(R.string.selected_popup, item.getTitle()));
            return true;
        }
        return false;
    }

    private void showProgrammaticMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenu().add(
                R.id.dynamic_group,
                R.id.dynamic_first,
                1,
                R.string.dynamic_first
        );
        popupMenu.getMenu().add(
                R.id.dynamic_group,
                R.id.dynamic_second,
                2,
                R.string.dynamic_second
        );
        popupMenu.setOnMenuItemClickListener(item -> {
            resultText.setText(getString(R.string.dynamic_selected, item.getTitle()));
            return true;
        });
        popupMenu.setOnDismissListener(menu -> eventText.setText(R.string.popup_closed));
        popupMenu.show();
        eventText.setText(R.string.popup_opened);
    }
}
