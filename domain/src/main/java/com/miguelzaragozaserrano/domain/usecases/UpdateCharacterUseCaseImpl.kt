package com.miguelzaragozaserrano.domain.usecases

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

typealias UpdateCharacter = BaseUseCase<@JvmSuppressWildcards Result<Boolean>, UpdateCharacterUseCaseImpl.Params>

@Singleton
class UpdateCharacterUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    UpdateCharacter() {

    override fun run(params: Params?): Flow<Result<@JvmSuppressWildcards Boolean>> =
        flow {
            runCatching {
                repository.updateCharacter(params?.id.orEmpty(), params?.status.orEmpty())
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(either.error))
                }
            }
        }

    data class Params(val id: Int, val status: Boolean)
}