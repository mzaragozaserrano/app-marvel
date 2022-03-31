package com.miguelzaragozaserrano.data.utils

import retrofit2.HttpException
import java.io.IOException

sealed class Result<out T : Any>

class Success<out T : Any>(val data: T) : Result<T>()

class Error(
    val failure: Failure
) : Result<Nothing>()

sealed class Failure {
    object Connectivity : Failure()
    class Server(val code: Int) : Failure()
    class Unknown(val message: String?) : Failure()
    data class Throwable(val throwable: kotlin.Throwable?) : Failure()
}

fun Exception.toError(): Failure = when (this) {
    is IOException -> Failure.Connectivity
    is HttpException -> Failure.Server(code())
    else -> Failure.Unknown(message ?: "")
}