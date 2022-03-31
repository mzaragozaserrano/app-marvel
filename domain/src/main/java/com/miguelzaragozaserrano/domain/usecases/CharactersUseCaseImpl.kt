package com.miguelzaragozaserrano.domain.usecases

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.repositories.CharactersRepository
import com.miguelzaragozaserrano.data.utils.Either
import com.miguelzaragozaserrano.data.utils.Error
import com.miguelzaragozaserrano.data.utils.Failure
import com.miguelzaragozaserrano.data.utils.Success
import com.miguelzaragozaserrano.domain.base.BaseUseCase
import com.miguelzaragozaserrano.domain.utils.extensions.orEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersUseCase = BaseUseCase<Either<@JvmSuppressWildcards Failure, @JvmSuppressWildcards Characters>, CharactersUseCaseImpl.Params>

@Singleton
class CharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    CharactersUseCase() {

    override fun run(params: Params?): Flow<Either<@JvmSuppressWildcards Failure, @JvmSuppressWildcards Characters>> =
        flow {
            runCatching {
                repository.getCharacters(params?.fromPagination.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> {
                        when (either.success) {
                            is Error -> emit(Either.Left((either.success as Error).failure))
                            is Success -> emit(Either.Right((either.success as Success<Characters>).data))
                        }
                    }
                    is Either.Left -> emit(Either.Left(either.error))
                }
            }
        }

    data class Params(val fromPagination: Boolean)
}