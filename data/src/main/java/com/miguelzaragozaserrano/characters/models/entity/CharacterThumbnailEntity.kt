package com.miguelzaragozaserrano.characters.models.entity

import com.miguelzaragozaserrano.characters.models.response.CharacterThumbnail
import com.miguelzaragozaserrano.characters.utils.extensions.empty

data class CharacterThumbnailEntity(val path: String?, val extension: String?, val image: String?) {

    companion object {
        fun empty() = CharacterThumbnailEntity(String.empty(), String.empty(), String.empty())
    }

    fun toCharacterThumbnail() = CharacterThumbnail(path, extension, image)
}