package com.miguelzaragozaserrano.characters.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.miguelzaragozaserrano.characters.data.models.response.Characters
import com.miguelzaragozaserrano.characters.data.utils.CharacterConverter
import com.miguelzaragozaserrano.core.extensions.empty

@Entity
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    @TypeConverters(CharacterConverter::class)
    var results: MutableList<CharacterEntity>?,
) {
    companion object {
        fun empty() =
            CharactersEntity(
                Int.empty(),
                Int.empty(),
                Int.empty(),
                Int.empty(),
                Int.empty(),
                mutableListOf()
            )
    }

    fun toCharacters() =
        Characters(
            offset,
            limit, total, count, results?.map { it.toCharacter() }?.toMutableList()
        )
}