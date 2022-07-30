package com.miguelzaragozaserrano.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.miguelzaragozaserrano.data.base.BaseDao
import com.miguelzaragozaserrano.data.models.entity.CharacterEntity

@Dao
interface CharactersDAO : BaseDao<CharacterEntity> {

    @Query("SELECT COUNT(id) FROM CharacterEntity")
    fun characterCount(): Int

    @Query("SELECT * FROM CharacterEntity")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity LIMIT :limit OFFSET :offset")
    fun getSome(limit: Int = 10, offset: Int): List<CharacterEntity>

}