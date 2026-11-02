package ru.nstu.lab6.kotlinapp

import android.content.Context
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar

object SnackbarHelper {
    fun configure(context: Context, snackbar: Snackbar) {
        val snackbarView = snackbar.view
        val layoutParams = snackbarView.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            val margin = context.resources.getDimensionPixelSize(R.dimen.snackbar_margin)
            layoutParams.setMargins(margin, margin, margin, margin)
            snackbarView.layoutParams = layoutParams
        }
        snackbarView.setBackgroundResource(R.drawable.bg_snackbar_rounded)
        ViewCompat.setElevation(
            snackbarView,
            context.resources.getDimension(R.dimen.snackbar_elevation),
        )
        snackbar.setTextColor(context.getColor(R.color.snackbar_text))
        snackbar.setActionTextColor(context.getColor(R.color.amber_accent))
    }
}
