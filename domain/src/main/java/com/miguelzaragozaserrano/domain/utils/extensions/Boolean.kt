package com.miguelzaragozaserrano.domain.utils.extensions

fun Boolean?.orEmpty(): Boolean = this ?: false
