package com.miguelzaragozaserrano.core.exception

sealed class Failure {
    data class Throwable(val throwable: kotlin.Throwable?) : Failure()
    data class CustomError(val errorCode: Int, val errorMessage: String?) : Failure()
}

data class FailureView(val title: String? = null, val message: String? = null)

fun Failure.toView(): FailureView = when (this) {
    is Failure.CustomError, is Failure.Throwable -> {
        FailureView(
            title = "Something happened",
            message = "Try again later"
        )
    }
}