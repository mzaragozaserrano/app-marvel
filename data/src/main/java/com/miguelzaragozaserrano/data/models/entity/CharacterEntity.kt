package com.miguelzaragozaserrano.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguelzaragozaserrano.data.models.response.Character
import com.miguelzaragozaserrano.data.models.response.CharacterThumbnail
import com.miguelzaragozaserrano.data.utils.extensions.empty

@Entity
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val path: String?,
    val extension: String?,
    val favorite: Boolean?,
) {
    companion object {
        fun empty() =
            CharacterEntity(
                Int.empty(),
                String.empty(),
                String.empty(),
                null,
                String.empty(),
                String.empty(),
                String.empty(),
                null
            )
    }

    fun toCharacter() =
        Character(id,
            name,
            description,
            modified,
            resourceURI,
            CharacterThumbnail(path.toString(), extension.toString()), favorite)

}