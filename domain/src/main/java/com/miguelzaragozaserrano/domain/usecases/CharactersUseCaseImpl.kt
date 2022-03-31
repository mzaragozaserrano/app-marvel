package com.miguelzaragozaserrano.domain.usecases

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.repositories.CharactersRepository
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Result
import com.miguelzaragozaserrano.data.utils.extensions.orEmpty
import com.miguelzaragozaserrano.domain.base.BaseUseCase
import com.miguelzaragozaserrano.domain.utils.extensions.orEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersUseCase = BaseUseCase<@JvmSuppressWildcards Result<Characters>, CharactersUseCaseImpl.Params>

@Singleton
class CharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    CharactersUseCase() {

    override fun run(params: Params?): Flow<Result<@JvmSuppressWildcards Characters>> =
        flow {
            runCatching {
                repository.getCharacters(params?.fromPagination.orEmpty(), params?.offset.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(either.error))
                }
            }
        }

    data class Params(val fromPagination: Boolean, val offset: Int)
}