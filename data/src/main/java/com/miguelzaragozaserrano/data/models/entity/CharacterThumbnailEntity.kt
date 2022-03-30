package com.miguelzaragozaserrano.data.models.entity

import com.miguelzaragozaserrano.data.models.response.CharacterThumbnail
import com.miguelzaragozaserrano.data.utils.extensions.empty

data class CharacterThumbnailEntity(val path: String?, val extension: String?, val image: String?) {

    companion object {
        fun empty() = CharacterThumbnailEntity(String.empty(), String.empty(), String.empty())
    }

    fun toCharacterThumbnail() = CharacterThumbnail(path, extension, image)
}