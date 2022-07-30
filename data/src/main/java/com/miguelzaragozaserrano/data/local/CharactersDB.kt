package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.State

interface CharactersDB {
    suspend fun getCharacterCount(): Int
    suspend fun getCharacters(offset: Int?): State<Characters>
    suspend fun saveCharacters(characters: Characters)
}