package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.models.entity.CharactersEntity

interface CharactersDB {
    fun getCharacters(): CharactersEntity?
    fun putCharacters(characters: CharactersEntity)
    fun updateCharacters(characters: CharactersEntity)
    fun getOffset(): Int?
}