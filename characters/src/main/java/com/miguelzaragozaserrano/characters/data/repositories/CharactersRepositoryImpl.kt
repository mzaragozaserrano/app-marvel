package com.miguelzaragozaserrano.characters.data.repositories

import com.miguelzaragozaserrano.characters.data.datasource.CharactersDataSource
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : CharactersRepository {

    override fun getCharacters(fromPagination: Boolean) =
        charactersDataSource.getCharacters(fromPagination)
}
