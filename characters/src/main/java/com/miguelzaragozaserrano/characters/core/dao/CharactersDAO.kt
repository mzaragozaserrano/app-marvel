package com.miguelzaragozaserrano.characters.core.dao

import androidx.room.Dao
import androidx.room.Query
import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.core.base.BaseDao

@Dao
interface CharactersDAO : BaseDao<CharactersEntity> {

    @Query("SELECT * FROM CharactersEntity")
    fun getCharacters(): CharactersEntity?

    @Query("SELECT `offset` FROM CharactersEntity")
    fun getOffset(): Int?
}
