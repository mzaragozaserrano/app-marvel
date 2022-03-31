package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.data.utils.Error
import com.miguelzaragozaserrano.marvel.utils.Status

data class UiState<T, G>(
    var status: Status = Status.LOADING,
    var error: Error = Error.Unknown(""),
    var success: G? = null
)