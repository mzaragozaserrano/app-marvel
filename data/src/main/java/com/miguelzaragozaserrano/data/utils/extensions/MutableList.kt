package com.miguelzaragozaserrano.data.utils.extensions

fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()
