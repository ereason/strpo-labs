package ru.nstu.lab5.javaapp;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button textToastButton = findViewById(R.id.textToastButton);
        textToastButton.setOnClickListener(view ->
                Toast.makeText(
                        getApplicationContext(),
                        R.string.java_toast_message,
                        Toast.LENGTH_SHORT
                ).show()
        );

        Button imageToastButton = findViewById(R.id.imageToastButton);
        imageToastButton.setOnClickListener(view -> showImageToast());

        Button customToastButton = findViewById(R.id.customToastButton);
        customToastButton.setOnClickListener(view -> showCustomToast());
    }

    private void showImageToast() {
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER);
        content.setPadding(dp(20), dp(16), dp(20), dp(16));

        GradientDrawable background = new GradientDrawable();
        background.setColor(getColor(R.color.lab_primary_dark));
        background.setCornerRadius(dp(24));
        content.setBackground(background);

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.ic_swift);
        content.addView(image, new LinearLayout.LayoutParams(dp(72), dp(72)));

        TextView message = new TextView(this);
        message.setText(R.string.swift_toast_message);
        message.setTextColor(getColor(R.color.white));
        message.setTextSize(16);
        LinearLayout.LayoutParams messageParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        messageParams.topMargin = dp(8);
        content.addView(message, messageParams);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, dp(96));
        toast.setView(content);
        toast.show();
    }

    private void showCustomToast() {
        FrameLayout parent = new FrameLayout(this);
        View content = getLayoutInflater().inflate(R.layout.custom_toast, parent, false);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, dp(96));
        toast.setView(content);
        toast.show();
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
