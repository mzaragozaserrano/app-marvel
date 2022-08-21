package com.miguelzaragozaserrano.domain.utils.extensions

fun Boolean?.orEmpty(): Boolean = this ?: false

infix fun <T> Boolean.then(param: T): T? = if (this) param else null