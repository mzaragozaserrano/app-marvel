package com.miguelzaragozaserrano.characters.local

import com.miguelzaragozaserrano.characters.dao.CharactersDAO
import com.miguelzaragozaserrano.characters.models.entity.CharactersEntity
import javax.inject.Inject

class CharactersLocal @Inject constructor(private val charactersDAO: CharactersDAO) : CharactersDB {
    override fun putCharacters(characters: CharactersEntity) = charactersDAO.insert(characters)

    override fun updateCharacters(characters: CharactersEntity) = charactersDAO.update(characters)

    override fun getCharacters() = charactersDAO.getCharacters()

    override fun getOffset() = charactersDAO.getOffset()
}