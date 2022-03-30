package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Error

interface CharactersDataSource {

    suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters>
}