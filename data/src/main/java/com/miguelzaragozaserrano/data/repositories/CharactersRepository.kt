package com.miguelzaragozaserrano.data.repositories

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Failure
import com.miguelzaragozaserrano.data.utils.Result

interface CharactersRepository {
    suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters>
}