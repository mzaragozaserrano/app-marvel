package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.dao.CharactersDAO
import javax.inject.Inject

class CharactersLocal @Inject constructor(private val charactersDAO: CharactersDAO) : CharactersDB {
    override fun getCharacters() = charactersDAO.getCharacters()
}