package com.miguelzaragozaserrano.data.utils

import com.miguelzaragozaserrano.data.utils.CodeError.*

typealias Result<T> = Either<Failure, Success<T>>

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()
class Failure(val error: Error) : State<Nothing>()

sealed class Error {
    class NoResults(val code: CodeError = NO_RESULTS) : Error()
    class Server(val code: CodeError = SERVER_ERROR) : Error()
    class Unknown(val code: CodeError = GENERIC_ERROR) : Error()
    data class Connectivity(val code: CodeError = NO_CONNECTIVITY) : Error()
    data class Throwable(val throwable: kotlin.Throwable?) : Error()
}

enum class CodeError {
    NO_RESULTS, NO_CONNECTIVITY, GENERIC_ERROR, SERVER_ERROR
}