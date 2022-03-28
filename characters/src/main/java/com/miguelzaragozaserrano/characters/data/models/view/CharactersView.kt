package com.miguelzaragozaserrano.characters.data.models.view

import com.miguelzaragozaserrano.characters.data.models.response.Characters
import com.miguelzaragozaserrano.core.extensions.empty

data class CharactersView(
    val offset: Int,
    val results: MutableList<CharacterView>
) {
    companion object {
        fun empty() =
            CharactersView(Int.empty(), mutableListOf())
    }

    fun toCharacters() =
        Characters(offset, results.map { it.toCharacter() }.toMutableList())
}