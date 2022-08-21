package com.miguelzaragozaserrano.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.miguelzaragozaserrano.data.base.BaseDao
import com.miguelzaragozaserrano.data.models.entity.CharacterEntity

@Dao
interface CharactersDAO : BaseDao<CharacterEntity> {

    @Query("SELECT COUNT(id) FROM CharacterEntity")
    fun getCharacterCount(): Int

    @Query("SELECT * FROM CharacterEntity ORDER BY name ASC")
    fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity ORDER BY name ASC LIMIT :limit OFFSET :offset")
    fun getSome(limit: Int = 10, offset: Int): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE favorite = :favorite")
    fun getFavorites(favorite: Boolean = true): List<CharacterEntity>

    @Query("UPDATE CharacterEntity SET favorite = :favorite WHERE id = :id")
    suspend fun updateFavorite(id: String, favorite: Boolean)

}