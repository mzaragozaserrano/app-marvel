package com.miguelzaragozaserrano.characters.utils.extensions

fun <T> MutableList<T>?.orEmpty(): MutableList<T> = this ?: mutableListOf()
