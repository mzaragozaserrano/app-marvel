package com.miguelzaragozaserrano.data.utils

import retrofit2.HttpException
import java.io.IOException

sealed class Error {
    object Connectivity : Error()
    class Server(val code: Int) : Error()
    class Unknown(val message: String?) : Error()
}

fun Exception.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    else -> Error.Unknown(message ?: "")
}