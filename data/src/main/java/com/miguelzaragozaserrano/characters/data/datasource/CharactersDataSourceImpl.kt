package com.miguelzaragozaserrano.characters.data.datasource

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.data.service.CharactersService
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.functional.Either
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val charactersService: CharactersService,
) : CharactersDataSource {

    override suspend fun getCharacters(fromPagination: Boolean): Either<Error, CharactersView> {
        return runCatching {
            getCharactersFromService()
        }.map {
            if (it != null) {
                Either.Right(success = it)
            } else {
                Either.Left(error = Error.Unknown("Can't access to the server"))
            }
        }.getOrElse {
            Either.Left(error = Error.Unknown(it.message))
        }
    }

    /*   val local = local.getCharacters()
       if (local != null && !fromPagination) {
           emit(Success(local.toCharacters().toCharactersView()))
       } else {
           emit(getCharactersFromService())
       }*/

    private suspend fun getCharactersFromService(): CharactersView? =
        charactersService.getCharacters(10, 50).body()?.data?.toCharactersView()

    /*return if (networkHandler.isConnected()) {
        service.getCharacters(10, calculateOffset()).run {
            if (isSuccessful && body() != null) {
                val data = body()?.data
                requireNotNull(data)
                saveLocal(data.toCharactersEntity())
                Success(data.toCharactersView())
            } else {
                Error(Failure.ServerError(code()))
            }
        }
    } else {
        Error(Failure.NetworkConnection)
    }*/

    /* return charactersService.getCharacters(10, 0).run {
         if (isSuccessful && body() != null) {
             val data = body()?.data
             requireNotNull(data)
             //saveLocal(data.toCharactersEntity())
             Result()
         } else {
             Error(Failure.ServerError(code()))
         }
     }*/

    //private fun calculateOffset() = local.getOffset()?.let { it + 10 }.orEmpty()

    /* private fun saveLocal(data: CharactersEntity) {
         val localCache = local.getCharacters()
         if (localCache != null) {
             localCache.offset = localCache.offset?.plus(10)
             localCache.results?.addAll(data.results.orEmpty())
             local.updateCharacters(localCache)
         } else {
             local.putCharacters(data)
         }
     }*/
}