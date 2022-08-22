package com.miguelzaragozaserrano.marvel.utils.extensions

import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miguelzaragozaserrano.marvel.R

fun FloatingActionButton.bindFabIcon(status: Boolean) {
    if (status) {
        this.setImageDrawable(AppCompatResources.getDrawable(
            rootView.context,
            R.drawable.ic_favorite_on))
    } else {
        this.setImageDrawable(
            AppCompatResources.getDrawable(
                rootView.context, R.drawable.ic_favorite_off))
    }
}