package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.data.utils.Error
import com.miguelzaragozaserrano.data.utils.Error.Unknown
import com.miguelzaragozaserrano.marvel.utils.Status
import com.miguelzaragozaserrano.marvel.utils.Status.LOADING

data class UiState<T, G>(
    var status: Status = LOADING,
    var error: Error = Unknown(),
    var success: G? = null
)