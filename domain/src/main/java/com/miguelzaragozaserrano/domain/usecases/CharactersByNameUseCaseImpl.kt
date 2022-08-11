package com.miguelzaragozaserrano.domain.usecases

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.repositories.CharactersRepository
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Result
import com.miguelzaragozaserrano.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

typealias FindCharactersByName = BaseUseCase<@JvmSuppressWildcards Result<Characters>, CharactersByNameUseCaseImpl.Params>

@Singleton
class CharactersByNameUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    FindCharactersByName() {

    override fun run(params: Params?): Flow<Result<@JvmSuppressWildcards Characters>> =
        flow {
            runCatching {
                repository.getCharactersByName(params?.query.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(either.error))
                }
            }
        }

    data class Params(val query: String)
}