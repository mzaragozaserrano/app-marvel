package com.miguelzaragozaserrano.data.service

import com.miguelzaragozaserrano.data.models.entity.CharacterEntity
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.base.BaseResponse
import com.miguelzaragozaserrano.data.utils.DataConstants.LIMIT
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
        @Query("limit") limit: Int? = LIMIT,
        @Query("offset") offset: Int? = 0,
    ): Response<BaseResponse<Characters>>

    @GET(CHARACTER)
    suspend fun getCharacter(@Path("characterId") id: String?): Response<BaseResponse<CharacterEntity>>
}
