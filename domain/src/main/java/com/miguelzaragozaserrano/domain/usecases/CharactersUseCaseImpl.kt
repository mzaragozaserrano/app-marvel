package com.miguelzaragozaserrano.domain.usecases

import com.miguelzaragozaserrano.characters.models.response.Characters
import com.miguelzaragozaserrano.characters.repositories.CharactersRepository
import com.miguelzaragozaserrano.characters.utils.Either
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.domain.base.BaseUseCase
import com.miguelzaragozaserrano.domain.utils.extensions.orEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersUseCase = BaseUseCase<Either<@JvmSuppressWildcards Error, @JvmSuppressWildcards Characters>, CharactersUseCaseImpl.Params>

@Singleton
class CharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    CharactersUseCase() {

    override fun run(params: Params?): Flow<Either<@JvmSuppressWildcards Error, @JvmSuppressWildcards Characters>> =
        flow {
            runCatching {
                repository.getCharacters(params?.fromPagination.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(Error.Unknown(
                        "Can't access to the server")))
                }
            }
        }

    data class Params(val fromPagination: Boolean)
}