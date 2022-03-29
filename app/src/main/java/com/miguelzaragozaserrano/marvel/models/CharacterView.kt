package com.miguelzaragozaserrano.marvel.models

import android.os.Parcelable
import com.miguelzaragozaserrano.characters.models.response.Character
import com.miguelzaragozaserrano.characters.models.response.CharacterThumbnail
import com.miguelzaragozaserrano.characters.utils.extensions.empty
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterView(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String?,
    val resourceURI: String,
    val favorite: Boolean?,
    val image: String,
) : Parcelable {
    companion object {
        fun empty() =
            CharacterView(
                Int.empty(),
                String.empty(),
                String.empty(),
                null,
                String.empty(),
                null,
                String.empty()
            )
    }

    fun toCharacter() =
        Character(id, name, description, modified, resourceURI, CharacterThumbnail.thumbail(image))
}