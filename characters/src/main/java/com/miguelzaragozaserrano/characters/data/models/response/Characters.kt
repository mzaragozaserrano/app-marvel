package com.miguelzaragozaserrano.characters.data.models.response

import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.core.extensions.orEmpty

data class Characters(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: MutableList<Character>?,
) {
    constructor(offset: Int, results: MutableList<Character>) : this(
        offset,
        null,
        null,
        null,
        results
    )

    fun toCharactersEntity() =
        CharactersEntity(
            null,
            offset,
            limit,
            total,
            count,
            mutableListOf()
        )

    fun toCharactersView() =
        CharactersView(
            offset.orEmpty(),
            results?.map { it.toCharacterView() }?.toMutableList().orEmpty()
        )
}