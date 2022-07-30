package com.miguelzaragozaserrano.data.service

import retrofit2.Retrofit
import javax.inject.Inject

class CharactersService @Inject constructor(retrofit: Retrofit) : CharactersApi {

    private val characterApi by lazy { retrofit.create(CharactersApi::class.java) }

    override suspend fun getCharacters(limit: Int?, offset: Int?) =
        characterApi.getCharacters(offset = offset)

    override suspend fun getCharacter(id: String?) = characterApi.getCharacter(id)
}