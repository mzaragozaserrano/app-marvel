package com.miguelzaragozaserrano.marvel.utils.extensions

import kotlinx.coroutines.Job

fun Job?.cancelIfActive() {
    if (this?.isActive == true) {
        cancel()
    }
}
