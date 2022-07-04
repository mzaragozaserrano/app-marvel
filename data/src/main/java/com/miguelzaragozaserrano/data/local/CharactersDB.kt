package com.miguelzaragozaserrano.data.local

import com.miguelzaragozaserrano.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.data.models.response.Characters

interface CharactersDB {
    fun getCharacters(): CharactersEntity?
}