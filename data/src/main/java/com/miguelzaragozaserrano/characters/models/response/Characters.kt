package com.miguelzaragozaserrano.characters.models.response

import com.miguelzaragozaserrano.characters.models.entity.CharactersEntity

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

}