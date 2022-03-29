package com.miguelzaragozaserrano.characters.data.datasource

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.functional.Either

interface CharactersDataSource {

    suspend fun getCharacters(fromPagination: Boolean): Either<Error, CharactersView>
}