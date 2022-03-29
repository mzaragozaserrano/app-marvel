package com.miguelzaragozaserrano.characters.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.miguelzaragozaserrano.characters.core.dao.CharactersDAO
import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.characters.data.utils.CharacterConverter

@Database(entities = [CharactersEntity::class], version = 1)
@TypeConverters(CharacterConverter::class)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDAO
}
