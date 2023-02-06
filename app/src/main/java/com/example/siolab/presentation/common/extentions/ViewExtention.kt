package com.example.siolab.presentation.common.extentions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, isShort: Boolean) {
    Snackbar.make(
        this,
        message,
        if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    ).show()
}