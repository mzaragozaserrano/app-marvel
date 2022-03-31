package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Failure
import com.miguelzaragozaserrano.data.utils.Result

interface CharactersDataSource {

    suspend fun getCharacters(fromPagination: Boolean): Either<Failure, Result<Characters>>
}