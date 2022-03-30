package com.miguelzaragozaserrano.marvel.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseActivity

fun Fragment.showProgressDialog() {
    (activity as BaseActivity<*>).showProgressDialog()
}

fun Fragment.hideProgressDialog() {
    (activity as BaseActivity<*>).hideProgressDialog()
}

fun Fragment.snackBarLong(
    view: View,
    text: String,
    colorBackground: Int = R.color.purple_700,
    colorText: Int = R.color.white
) {
    (activity as BaseActivity<*>).snackBarLong(view, text, colorBackground, colorText)
}