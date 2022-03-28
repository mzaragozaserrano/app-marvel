package com.miguelzaragozaserrano.characters.data.local

import com.miguelzaragozaserrano.characters.core.dao.CharactersDAO
import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity
import javax.inject.Inject

class CharacterLocal @Inject constructor(private val charactersDAO: CharactersDAO) : CharacterDB {
    override fun putCharacters(characters: CharactersEntity) = charactersDAO.insert(characters)

    override fun updateCharacters(characters: CharactersEntity) = charactersDAO.update(characters)

    override fun getCharacters() = charactersDAO.getCharacters()

    override fun getOffset() = charactersDAO.getOffset()
}