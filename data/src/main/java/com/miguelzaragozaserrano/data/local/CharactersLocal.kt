package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.dao.CharactersDAO
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.models.response.TYPE
import com.miguelzaragozaserrano.data.models.response.TYPE.ALL
import com.miguelzaragozaserrano.data.models.response.TYPE.FAVORITE
import com.miguelzaragozaserrano.data.utils.DataConstants.LIMIT
import com.miguelzaragozaserrano.data.utils.State
import com.miguelzaragozaserrano.data.utils.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersLocal @Inject constructor(private val charactersDAO: CharactersDAO) : CharactersDB {

    override suspend fun getCharacterCount(): Int =
        withContext(Dispatchers.IO) { charactersDAO.getCharacterCount() }

    override suspend fun getCharacters(offset: Int?): State<Characters> =
        withContext(Dispatchers.IO) {
            val list = if (offset == null) {
                charactersDAO.getAll()
            } else {
                charactersDAO.getSome(LIMIT, offset)
            }
            val characters =
                Characters(0, ALL, LIMIT, 0, 0, list.map { it.toCharacter() }.toMutableList())
            Success(characters)
        }

    override suspend fun getFavoriteCharacters(): State<Characters> =
        withContext(Dispatchers.IO) {
            val list = charactersDAO.getFavorites()
            val characters =
                Characters(0, FAVORITE, LIMIT, 0, 0, list.map { it.toCharacter() }.toMutableList())
            Success(characters)
        }

    override suspend fun saveCharacters(characters: Characters) {
        characters.results?.let {
            withContext(Dispatchers.IO) {
                charactersDAO.insertAll(it.toList().map { it.toCharacterEntity() })
            }
        }
    }

    override suspend fun updateCharacter(id: Int, status: Boolean): State<Boolean> =
        withContext(Dispatchers.IO) {
            charactersDAO.updateFavorite(id, status)
            Success(true)
        }

}