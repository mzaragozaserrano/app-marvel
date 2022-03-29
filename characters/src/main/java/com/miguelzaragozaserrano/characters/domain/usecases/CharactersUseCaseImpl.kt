package com.miguelzaragozaserrano.characters.domain.usecases

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import com.miguelzaragozaserrano.core.base.BaseUseCase
import com.miguelzaragozaserrano.core.extensions.orEmpty
import com.miguelzaragozaserrano.core.functional.State
import javax.inject.Inject
import javax.inject.Singleton

typealias CharactersUseCase = BaseUseCase<State<@JvmSuppressWildcards CharactersView>, CharactersUseCaseImpl.Params>

@Singleton
class CharactersUseCaseImpl @Inject constructor(private val repository: CharactersRepository) :
    CharactersUseCase() {

    override fun run(params: Params?) = repository.getCharacters(params?.fromPagination.orEmpty())

    data class Params(val fromPagination: Boolean)
}