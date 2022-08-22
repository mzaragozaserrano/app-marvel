package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.data.utils.Error
import com.miguelzaragozaserrano.data.utils.Error.Unknown
import com.miguelzaragozaserrano.marvel.utils.Status
import com.miguelzaragozaserrano.marvel.utils.Status.LOADED

data class UiState<T, G>(
    var status: Status = LOADED,
    var error: Error = Unknown(),
    var success: G? = null,
)