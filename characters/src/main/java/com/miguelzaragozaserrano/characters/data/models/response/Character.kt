package com.miguelzaragozaserrano.characters.data.models.response

import com.miguelzaragozaserrano.characters.data.models.entity.CharacterEntity
import com.miguelzaragozaserrano.characters.data.models.view.CharacterView
import com.miguelzaragozaserrano.core.extensions.orEmpty

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

    fun toCharacterView() = CharacterView(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        modified = modified,
        resourceURI = resourceURI.orEmpty(),
        favorite = null,
        image = thumbnail?.image().orEmpty()
    )
}