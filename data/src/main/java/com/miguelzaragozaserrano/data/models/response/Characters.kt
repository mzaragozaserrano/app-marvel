package com.miguelzaragozaserrano.data.models.response

data class Characters(
    val offset: Int?,
    val state: TYPE?,
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
        null,
        results
    )

}

enum class TYPE { ALL, FAVORITE }