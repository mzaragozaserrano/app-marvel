package com.miguelzaragozaserrano.characters.models.response

import com.miguelzaragozaserrano.characters.models.entity.CharacterEntity
import com.miguelzaragozaserrano.characters.utils.extensions.orEmpty

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: CharacterThumbnail?,
) {

    fun toCharacterEntity() = CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        characterImage = thumbnail?.toCharacterThumbnailEntity(),
        favorite = null
    )
}