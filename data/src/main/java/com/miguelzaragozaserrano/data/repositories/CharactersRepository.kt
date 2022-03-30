package com.miguelzaragozaserrano.data.repositories

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Error

interface CharactersRepository {
    suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters>
}