package com.miguelzaragozaserrano.characters.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.miguelzaragozaserrano.characters.models.entity.CharacterEntity
import com.miguelzaragozaserrano.characters.utils.extensions.empty

object CharacterConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toCharacter(data: String?): MutableList<CharacterEntity> {
        return if (data.isNullOrEmpty()) {
            mutableListOf()
        } else {
            gson.fromJson(data, Array<CharacterEntity>::class.java).map { it }
                .toMutableList()
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromCharacter(data: MutableList<CharacterEntity>?): String {
        return if (data == null) {
            String.empty()
        } else {
            gson.toJson(data)
        }
    }
}
