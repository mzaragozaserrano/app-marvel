package com.miguelzaragozaserrano.characters.domain.repository

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.functional.Either

interface CharactersRepository {
    suspend fun getCharacters(fromPagination: Boolean): Either<Error, CharactersView>
}