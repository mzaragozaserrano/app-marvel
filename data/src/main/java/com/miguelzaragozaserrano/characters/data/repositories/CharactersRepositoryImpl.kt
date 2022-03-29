package com.miguelzaragozaserrano.characters.data.repositories

import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.functional.Either
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {

    override suspend fun getCharacters(fromPagination: Boolean): Either<Error, CharactersView> =
        charactersDataSource.getCharacters(fromPagination)
}
