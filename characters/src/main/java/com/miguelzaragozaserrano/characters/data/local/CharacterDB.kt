package com.miguelzaragozaserrano.characters.data.local

import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity

interface CharacterDB {
    fun getCharacters(): CharactersEntity?
    fun putCharacters(characters: CharactersEntity)
    fun updateCharacters(characters: CharactersEntity)
    fun getOffset(): Int?
}