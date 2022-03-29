package com.miguelzaragozaserrano.characters.datasource

import com.miguelzaragozaserrano.characters.models.response.Characters
import com.miguelzaragozaserrano.characters.utils.Either
import com.miguelzaragozaserrano.characters.utils.Error

interface CharactersDataSource {

    suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters>
}