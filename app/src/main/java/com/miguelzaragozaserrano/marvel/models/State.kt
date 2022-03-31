package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.data.utils.Failure
import com.miguelzaragozaserrano.marvel.utils.Status

data class State<T>(
    var status: Status = Status.LOADING,
    var error: Failure = Failure.Unknown(""),
    var success: T? = null,
)