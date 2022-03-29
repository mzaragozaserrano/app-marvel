package com.miguelzaragozaserrano.characters.repositories

import com.miguelzaragozaserrano.characters.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.models.response.Characters
import com.miguelzaragozaserrano.characters.utils.Either
import com.miguelzaragozaserrano.characters.utils.Error
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {

    override suspend fun getCharacters(fromPagination: Boolean): Either<Error, Characters> =
        charactersDataSource.getCharacters(fromPagination)
}
