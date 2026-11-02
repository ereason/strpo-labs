package ru.nstu.lab6.javaapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.Snackbar;

final class SnackbarHelper {
    private SnackbarHelper() {
    }

    static void configure(Context context, Snackbar snackbar) {
        View snackbarView = snackbar.getView();
        ViewGroup.LayoutParams layoutParams = snackbarView.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginParams =
                    (ViewGroup.MarginLayoutParams) layoutParams;
            int margin = context.getResources().getDimensionPixelSize(R.dimen.snackbar_margin);
            marginParams.setMargins(margin, margin, margin, margin);
            snackbarView.setLayoutParams(marginParams);
        }
        snackbarView.setBackgroundResource(R.drawable.bg_snackbar_rounded);
        ViewCompat.setElevation(
                snackbarView,
                context.getResources().getDimension(R.dimen.snackbar_elevation)
        );
        snackbar.setTextColor(context.getColor(R.color.snackbar_text));
        snackbar.setActionTextColor(context.getColor(R.color.amber_accent));
    }
}
