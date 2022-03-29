package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.characters.models.response.Characters
import com.miguelzaragozaserrano.characters.utils.extensions.empty

data class CharactersView(
    val offset: Int,
    val results: MutableList<CharacterView>,
) {
    companion object {
        fun empty() =
            CharactersView(Int.empty(), mutableListOf())
    }

    fun toCharacters() =
        Characters(offset, results.map { it.toCharacter() }.toMutableList())
}