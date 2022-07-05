package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.dao.CharactersDAO
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.utils.State
import com.miguelzaragozaserrano.data.utils.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersLocal @Inject constructor(private val charactersDAO: CharactersDAO) : CharactersDB {

    override suspend fun saveCharacters(characters: Characters) {
        characters.results?.let {
            withContext(Dispatchers.IO) {
                charactersDAO.insertAll(it.toList().map { it.toCharacterEntity() })
            }
        }
    }

    override suspend fun getCharacterCount(): Int =
        withContext(Dispatchers.IO) { charactersDAO.characterCount() }

    override suspend fun getCharacters(): State<Characters> =
        withContext(Dispatchers.IO) {
            val list = charactersDAO.getAll()
            val characters = Characters(0, 20, 0, 0, list.map { it.toCharacter() }.toMutableList())
            Success(characters)
        }

}