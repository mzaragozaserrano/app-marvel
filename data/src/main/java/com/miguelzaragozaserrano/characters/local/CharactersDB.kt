package com.miguelzaragozaserrano.characters.local

import com.miguelzaragozaserrano.characters.models.entity.CharactersEntity

interface CharactersDB {
    fun getCharacters(): CharactersEntity?
    fun putCharacters(characters: CharactersEntity)
    fun updateCharacters(characters: CharactersEntity)
    fun getOffset(): Int?
}