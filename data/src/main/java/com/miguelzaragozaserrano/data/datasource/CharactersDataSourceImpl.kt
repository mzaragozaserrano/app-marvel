package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.service.CharactersService
import com.miguelzaragozaserrano.data.utils.*
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val charactersService: CharactersService,
) : CharactersDataSource {

    override suspend fun getCharacters(fromPagination: Boolean): Either<Failure, Result<Characters>> {
        return runCatching {
            getCharactersFromService()
        }.map {
            Either.Right(success = it)
        }.getOrElse {
            Either.Left(error = Failure.Throwable(it))
        }
    }

    /*   val local = local.getCharacters()
       if (local != null && !fromPagination) {
           emit(Success(local.toCharacters().toCharactersView()))
       } else {
           emit(getCharactersFromService())
       }*/

    private suspend fun getCharactersFromService(): Result<Characters> =
        if (networkHandler.isConnected()) {
            service.getCharacters(25, 0).run {
                if (isSuccessful && body() != null) {
                    val data = body()?.data
                    requireNotNull(data)
                    Success(data)
                } else {
                    Error(Failure.Server(code()))
                }
            }
        } else {
            Error(Failure.Connectivity)
        }
}
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