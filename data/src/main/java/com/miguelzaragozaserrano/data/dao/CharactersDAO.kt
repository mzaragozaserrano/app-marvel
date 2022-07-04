package com.miguelzaragozaserrano.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.miguelzaragozaserrano.data.base.BaseDao
import com.miguelzaragozaserrano.data.models.entity.CharactersEntity

@Dao
interface CharactersDAO : BaseDao<CharactersEntity> {

    @Query("SELECT * FROM CharactersEntity")
    fun getCharacters(): CharactersEntity?

}
