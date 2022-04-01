package com.miguelzaragozaserrano.data.utils.extensions

import java.math.BigInteger
import java.security.MessageDigest

fun String.Companion.empty() = ""

fun String?.orNotInfo(): String {
    return if (this.isNullOrEmpty()) {
        "Information not available."
    } else {
        this
    }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}