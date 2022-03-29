package com.miguelzaragozaserrano.characters.data.service

import com.miguelzaragozaserrano.characters.data.models.entity.CharacterEntity
import com.miguelzaragozaserrano.characters.data.models.response.Characters
import com.miguelzaragozaserrano.core.base.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    companion object {
        const val CHARACTERS = "/v1/public/characters"
        const val CHARACTER = "/v1/public/characters/{characterId}"
    }

    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query("limit") limit: Int? = 10,
        @Query("offset") offset: Int? = 0,
    ): Response<BaseResponse<Characters>>

    @GET(CHARACTER)
    suspend fun getCharacter(@Path("characterId") id: String?): Response<BaseResponse<CharacterEntity>>
}