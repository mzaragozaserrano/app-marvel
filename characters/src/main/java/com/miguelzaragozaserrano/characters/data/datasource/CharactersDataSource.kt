package com.miguelzaragozaserrano.characters.data.datasource

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.core.functional.State
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {

    fun getCharacters(fromPagination: Boolean): Flow<State<CharactersView>>
}