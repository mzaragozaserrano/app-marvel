package com.miguelzaragozaserrano.characters.domain.usecases

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.base.BaseUseCase
import com.miguelzaragozaserrano.core.extensions.orEmpty
import com.miguelzaragozaserrano.core.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersUseCase = BaseUseCase<Either<@JvmSuppressWildcards Error, @JvmSuppressWildcards CharactersView>, CharactersUseCaseImpl.Params>

@Singleton
class CharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    CharactersUseCase() {

    override fun run(params: Params?): Flow<Either<@JvmSuppressWildcards Error, @JvmSuppressWildcards CharactersView>> =
        flow {
            runCatching {
                repository.getCharacters(params?.fromPagination.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(Error.Unknown("Can't access to the server")))
                }
            }
        }

    data class Params(val fromPagination: Boolean)
}