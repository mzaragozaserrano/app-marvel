package com.miguelzaragozaserrano.characters.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguelzaragozaserrano.characters.dao.CharactersDAO
import com.miguelzaragozaserrano.characters.models.entity.CharactersEntity
import com.miguelzaragozaserrano.characters.utils.CharacterConverter

@Database(entities = [CharactersEntity::class], version = 1)
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDAO
}
