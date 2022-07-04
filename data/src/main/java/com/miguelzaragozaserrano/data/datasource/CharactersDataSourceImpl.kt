package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.service.CharactersService
import com.miguelzaragozaserrano.data.utils.*
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: CharactersService,
) : CharactersDataSource {

    override suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters> =
        runCatching {
            getCharactersFromService(offset)
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
            service.getCharacters(20, offset).run {
                if (isSuccessful && body() != null) {
                    val data = body()?.data
                    requireNotNull(data)
                    Success(data)
                } else {
                    Failure(Error.Server(code()))
                }
            }
        } else {
            Failure(Error.Connectivity())
        }
}