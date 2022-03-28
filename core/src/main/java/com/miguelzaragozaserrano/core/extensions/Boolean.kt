package com.miguelzaragozaserrano.core.extensions

fun Boolean?.orEmpty(): Boolean = this ?: false
