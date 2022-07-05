package com.miguelzaragozaserrano.data.models.response

import com.miguelzaragozaserrano.data.models.entity.CharacterThumbnailEntity

data class CharacterThumbnail(val path: String?, val extension: String?, val image: String?) {
    constructor(path: String, extension: String) : this(path, extension, null)
    constructor(image: String) : this(null, null, image)

    fun image(): String = "$path.$extension"

    fun toCharacterThumbnailEntity() = CharacterThumbnailEntity(path, extension, image)

    companion object {
        fun thumbail(image: String): CharacterThumbnail {
            val imageSplit = image.split(".")
            return CharacterThumbnail(image.removeSuffix(imageSplit[3]), imageSplit[3])
        }
    }
}