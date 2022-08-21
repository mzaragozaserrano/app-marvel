package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Result

interface CharactersDataSource {
    suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters>
    suspend fun getFavoriteCharacters(): Result<Characters>
}