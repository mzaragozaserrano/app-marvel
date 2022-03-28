package com.miguelzaragozaserrano.characters.data.datasource

import com.miguelzaragozaserrano.characters.data.local.CharactersLocal
import com.miguelzaragozaserrano.characters.data.models.entity.CharactersEntity
import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.data.service.CharactersService
import com.miguelzaragozaserrano.core.exception.Failure
import com.miguelzaragozaserrano.core.extensions.orEmpty
import com.miguelzaragozaserrano.core.functional.Error
import com.miguelzaragozaserrano.core.functional.State
import com.miguelzaragozaserrano.core.functional.Success
import com.miguelzaragozaserrano.core.utils.NetworkHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val service: CharactersService,
    private val local: CharactersLocal,
) : CharactersDataSource {

    override fun getCharacters(fromPagination: Boolean) = flow {
        val local = local.getCharacters()
        if (local != null && !fromPagination) {
            emit(Success(local.toCharacters().toCharactersView()))
        } else {
            emit(getCharactersFromService())
        }
    }.catch { emit(Error(Failure.Throwable(it))) }

    private suspend fun getCharactersFromService(): State<CharactersView> {
        return if (networkHandler.isConnected()) {
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
        }
    }

    private fun calculateOffset() = local.getOffset()?.let { it + 10 }.orEmpty()

    private fun saveLocal(characters: CharactersEntity) {
        val localCache = local.getCharacters()
        if (localCache != null) {
            localCache.offset = localCache.offset?.plus(10)
            localCache.results?.addAll(characters.results.orEmpty())
            local.updateCharacters(localCache)
        } else {
            local.putCharacters(characters)
        }
    }
}