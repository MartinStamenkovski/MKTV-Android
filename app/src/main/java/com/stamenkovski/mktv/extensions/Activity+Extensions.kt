package com.stamenkovski.mktv.extensions

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity.makeErrorSnackbarWith(message: String?, onView: View, listener: (View) -> Unit) {
    val snackBar = Snackbar.make(onView,message ?: "Error", Snackbar.LENGTH_INDEFINITE);
    snackBar.setAction("Retry", listener)
    snackBar.show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hidden() {
    visibility = View.INVISIBLE
}