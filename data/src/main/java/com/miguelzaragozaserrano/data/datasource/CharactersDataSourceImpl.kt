package com.miguelzaragozaserrano.data.datasource

import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.service.CharactersService
import com.miguelzaragozaserrano.data.utils.*
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: CharactersService,
) : CharactersDataSource {

    override suspend fun getCharacters(fromPagination: Boolean, offset: Int): Result<Characters> {
        return runCatching {
            getCharactersFromService(offset)
        }.map { state ->
            return when (state) {
                is Failure -> Either.Left(Failure(state.error))
                is Success -> Either.Right(Success(state.data))
            }
        }.getOrElse {
            Either.Left(error = Failure(Error.Throwable(it)))
        }
    }

    /*   val local = local.getCharacters()
       if (local != null && !fromPagination) {
           emit(Success(local.toCharacters().toCharactersView()))
       } else {
           emit(getCharactersFromService())
       }*/

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