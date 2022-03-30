package com.miguelzaragozaserrano.data.repositories

import com.miguelzaragozaserrano.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Error
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {

    override suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters> =
        charactersDataSource.getCharacters(fromPagination)
}
