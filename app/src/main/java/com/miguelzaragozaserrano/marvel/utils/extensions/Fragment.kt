package com.miguelzaragozaserrano.marvel.utils.extensions

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseActivity

fun Fragment.hideProgressDialog() {
    (activity as BaseActivity<*>).hideProgressDialog()
}

fun Fragment.setSupportActionBar(toolbar: Toolbar? = null) {
    (activity as BaseActivity<*>).setSupportActionBar(toolbar)
    (activity as BaseActivity<*>).supportActionBar?.setDisplayShowTitleEnabled(false)
    setHasOptionsMenu(true)
}

fun Fragment.showProgressDialog() {
    (activity as BaseActivity<*>).showProgressDialog()
}

fun Fragment.snackBarLong(
    view: View,
    text: String,
    colorBackground: Int = R.color.primaryColorOn,
    colorText: Int = R.color.white
) {
    (activity as BaseActivity<*>).snackBarLong(view, text, colorBackground, colorText)
}

fun Fragment.toastLong(message: String) {
    (activity as BaseActivity<*>).toastLong(message)
}