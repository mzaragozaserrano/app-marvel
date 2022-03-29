package com.miguelzaragozaserrano.characters.models.entity

import androidx.room.Entity
import com.miguelzaragozaserrano.characters.models.response.Character
import com.miguelzaragozaserrano.characters.utils.extensions.empty

@Entity
data class CharacterEntity(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val characterImage: CharacterThumbnailEntity?,
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
                CharacterThumbnailEntity.empty(),
                null
            )
    }

    fun toCharacter() =
        Character(id,
            name,
            description,
            modified,
            resourceURI,
            characterImage?.toCharacterThumbnail())
}