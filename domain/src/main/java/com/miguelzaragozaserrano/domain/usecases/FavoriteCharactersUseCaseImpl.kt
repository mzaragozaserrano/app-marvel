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

typealias GetFavoriteCharacters = BaseUseCase<@JvmSuppressWildcards Result<Characters>, FavoriteCharactersUseCaseImpl>

@Singleton
class FavoriteCharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    GetFavoriteCharacters() {

    override fun run(params: FavoriteCharactersUseCaseImpl?): Flow<Result<@JvmSuppressWildcards Characters>> =
        flow {
            runCatching {
                repository.getFavoriteCharacters()
            }.map { either ->
                when (either) {
                    is Either.Right -> emit(Either.Right(either.success))
                    is Either.Left -> emit(Either.Left(either.error))
                }
            }
        }
}