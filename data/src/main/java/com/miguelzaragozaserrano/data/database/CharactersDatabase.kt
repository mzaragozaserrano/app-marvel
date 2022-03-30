package com.miguelzaragozaserrano.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguelzaragozaserrano.data.dao.CharactersDAO
import com.miguelzaragozaserrano.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.data.utils.CharacterConverter

@Database(entities = [CharactersEntity::class], version = 1)
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDAO
}