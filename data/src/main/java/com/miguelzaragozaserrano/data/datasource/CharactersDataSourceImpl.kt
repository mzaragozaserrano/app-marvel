package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.local.CharactersLocal
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.service.CharactersService
import com.miguelzaragozaserrano.data.utils.*
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: CharactersService,
    private val local: CharactersLocal,
) : CharactersDataSource {

    override suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters> =
        runCatching {
            if (local.getCharacterCount() <= offset) {
                getCharactersFromService(offset)
            } else {
                local.getCharacters(offset)
            }
        }.map { state ->
            return when (state) {
                is Failure -> Either.Left(Failure(state.error))
                is Success -> Either.Right(Success(state.data))
            }
        }.getOrElse {
            Either.Left(error = Failure(Error.Throwable(it)))
        }

    override suspend fun getFavoriteCharacters(): Result<Characters> =
        runCatching {
            local.getFavoriteCharacters()
        }.map { state ->
            return when (state) {
                is Failure -> Either.Left(Failure(state.error))
                is Success -> Either.Right(Success(state.data))
            }
        }.getOrElse {
            Either.Left(error = Failure(Error.Throwable(it)))
        }

    override suspend fun updateCharacter(id: Int, status: Boolean): Result<Boolean> =
        runCatching {
            local.updateCharacter(id, status)
        }.map { state ->
            return when (state) {
                is Failure -> Either.Left(Failure(state.error))
                is Success -> Either.Right(Success(state.data))
            }
        }.getOrElse {
            Either.Left(error = Failure(Error.Throwable(it)))
        }

    private suspend fun getCharactersFromService(offset: Int): State<Characters> =
        if (networkHandler.isConnected()) {
            service.getCharacters(offset = offset).run {
                if (isSuccessful && body() != null) {
                    val data = body()?.data
                    requireNotNull(data)
                    local.saveCharacters(data)
                    Success(data)
                } else {
                    Failure(Error.Server())
                }
            }
        } else {
            Failure(Error.Connectivity())
        }

}