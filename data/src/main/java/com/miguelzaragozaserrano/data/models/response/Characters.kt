package com.miguelzaragozaserrano.data.models.response

import com.miguelzaragozaserrano.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.data.models.response.Character

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