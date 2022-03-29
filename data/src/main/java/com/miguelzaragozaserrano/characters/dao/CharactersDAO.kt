package com.miguelzaragozaserrano.characters.dao

import androidx.room.Dao
import androidx.room.Query
import com.miguelzaragozaserrano.characters.models.entity.CharactersEntity
import com.miguelzaragozaserrano.characters.base.BaseDao

@Dao
interface CharactersDAO : BaseDao<CharactersEntity> {

    @Query("SELECT * FROM CharactersEntity")
    fun getCharacters(): CharactersEntity?

    @Query("SELECT `offset` FROM CharactersEntity")
    fun getOffset(): Int?
}
