package com.miguelzaragozaserrano.characters.domain.repository

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(fromPagination: Boolean): Flow<State<CharactersView>>
}