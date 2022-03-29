package com.miguelzaragozaserrano.marvel.data.states

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.marvel.data.utils.Status

data class CharactersState(
    var status: Status = Status.LOADING,
    var error: Error = Error.Unknown(""),
    var success: CharactersView = CharactersView.empty(),
)