package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.marvel.utils.Status

data class CharactersState(
    var status: Status = Status.LOADING,
    var error: Error = Error.Unknown(""),
    var success: CharactersView = CharactersView.empty(),
)