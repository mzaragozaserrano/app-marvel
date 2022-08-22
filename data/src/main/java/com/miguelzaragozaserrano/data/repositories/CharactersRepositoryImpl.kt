package com.miguelzaragozaserrano.data.repositories

import com.miguelzaragozaserrano.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Result
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {
    override suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters> =
        charactersDataSource.getCharacters(fromPagination, offset)

    override suspend fun getFavoriteCharacters(): Result<Characters> =
        charactersDataSource.getFavoriteCharacters()

    override suspend fun updateCharacter(id: Int, status: Boolean): Result<Boolean> =
        charactersDataSource.updateCharacter(id, status)
}