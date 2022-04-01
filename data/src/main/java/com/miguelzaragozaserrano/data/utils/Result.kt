package com.miguelzaragozaserrano.data.utils

typealias Result<T> = Either<Failure, Success<T>>

sealed class State<out T : Any>

class Success<out T : Any>(val data: T) : State<T>()
class Failure(val error: Error) : State<Nothing>()

sealed class Error {
    class Server(val code: Int) : Error()
    class Unknown(val message: String?) : Error()
    data class Connectivity (val message: String? = "Error en el servidor") : Error()
    data class Throwable(val throwable: kotlin.Throwable?) : Error()
}