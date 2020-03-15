@file:JvmName("Toaster")

package com.stamenkovski.mktv.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast

private var toast: Toast? = null

fun shortToast(context: Context, message: String) {
    toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast?.show()
}

fun longToast(context: Context, message: String) {
    toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    toast?.show()
}

/**
 * @param rootView the root view of the Activity/Fragment obtained by findViewById(android.R.id.content)
 */
fun aboveKeyboard(context: Context, message: String, rootView: View?) {
    toast?.cancel()
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, (rootView?.height?.div(2)) ?: 0)
    toast?.show()
}