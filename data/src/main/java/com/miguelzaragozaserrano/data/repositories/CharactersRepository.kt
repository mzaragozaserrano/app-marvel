package com.miguelzaragozaserrano.data.repositories

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Result

interface CharactersRepository {
    suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters>
    suspend fun getFavoriteCharacters(): Result<Characters>
    suspend fun updateCharacter(id: Int, status: Boolean): Result<Boolean>
}