package com.miguelzaragozaserrano.marvel.models

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.models.response.TYPE
import com.miguelzaragozaserrano.data.utils.extensions.empty

data class CharactersView(
    val offset: Int,
    var state: TYPE?,
    var results: MutableList<CharacterView>,
) {
    companion object {
        fun empty() =
            CharactersView(Int.empty(), TYPE.ALL, mutableListOf())
    }

    fun toCharacters() =
        Characters(offset, results.map { it.toCharacter() }.toMutableList())
}