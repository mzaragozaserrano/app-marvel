package com.miguelzaragozaserrano.characters.repositories

import com.miguelzaragozaserrano.characters.models.response.Characters
import com.miguelzaragozaserrano.characters.utils.Either
import com.miguelzaragozaserrano.characters.utils.Error

interface CharactersRepository {
    suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters>
}