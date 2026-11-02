package ru.nstu.lab6.javaapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Lab6Snackbar";
    private Snackbar activeSnackbar;
    private TextView eventText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventText = findViewById(R.id.eventText);

        findViewById(R.id.basicButton).setOnClickListener(view ->
                showTracked(Snackbar.make(view, R.string.basic_message, Snackbar.LENGTH_LONG))
        );

        findViewById(R.id.durationButton).setOnClickListener(view ->
                showTracked(Snackbar.make(view, R.string.duration_message, 8000))
        );

        findViewById(R.id.indefiniteButton).setOnClickListener(view ->
                showTracked(Snackbar.make(view, R.string.indefinite_message, Snackbar.LENGTH_INDEFINITE))
        );

        findViewById(R.id.dismissButton).setOnClickListener(view -> {
            if (activeSnackbar != null) {
                activeSnackbar.dismiss();
            }
        });

        findViewById(R.id.colorButton).setOnClickListener(this::showColoredSnackbar);
        findViewById(R.id.roundedButton).setOnClickListener(this::showRoundedSnackbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this::showActionSnackbar);
    }

    private void showColoredSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.colored_message, Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(getColor(R.color.green_primary))
                .setTextColor(getColor(R.color.snackbar_text))
                .setActionTextColor(getColor(R.color.amber_accent))
                .setAction(R.string.dismiss_action, button -> updateEvent("нажата кнопка закрытия"));
        showTracked(snackbar);
    }

    private void showActionSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.action_message, Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(getColor(R.color.green_dark))
                .setTextColor(getColor(R.color.snackbar_text))
                .setActionTextColor(getColor(R.color.amber_accent))
                .setAction(R.string.action_yes, button ->
                        Toast.makeText(getApplicationContext(), R.string.action_result, Toast.LENGTH_SHORT).show()
                );
        showTracked(snackbar);
    }

    private void showRoundedSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view, R.string.rounded_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dismiss_action, button -> updateEvent("закругленная панель закрыта"));
        SnackbarHelper.configure(this, snackbar);
        showTracked(snackbar);
    }

    private void showTracked(Snackbar snackbar) {
        activeSnackbar = snackbar;
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onShown(Snackbar shownSnackbar) {
                updateEvent("Snackbar показан");
            }

            @Override
            public void onDismissed(Snackbar dismissedSnackbar, int event) {
                updateEvent(dismissReason(event));
                if (activeSnackbar == dismissedSnackbar) {
                    activeSnackbar = null;
                }
            }
        });
        snackbar.show();
    }

    private String dismissReason(int event) {
        switch (event) {
            case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                return "закрыт смахиванием";
            case Snackbar.Callback.DISMISS_EVENT_ACTION:
                return "закрыт действием";
            case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                return "закрыт по тайм-ауту";
            case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                return "закрыт вручную";
            case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                return "заменен новым Snackbar";
            default:
                return "закрыт";
        }
    }

    private void updateEvent(String event) {
        Log.i(TAG, event);
        eventText.setText(getString(R.string.event_format, event));
    }
}
