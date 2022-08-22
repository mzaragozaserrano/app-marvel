package com.miguelzaragozaserrano.data.models.response

import com.miguelzaragozaserrano.data.models.entity.CharacterEntity

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: String?,
    val resourceURI: String?,
    val thumbnail: CharacterThumbnail?,
    val favorite: Boolean?,
) {

    fun toCharacterEntity() = CharacterEntity(
        id = id,
        name = name,
        description = description,
        modified = modified,
        resourceURI = resourceURI,
        path = thumbnail?.path,
        extension = thumbnail?.extension,
        favorite = null
    )
}