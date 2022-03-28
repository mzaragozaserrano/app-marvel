package com.miguelzaragozaserrano.characters.domain.usecases

import com.miguelzaragozaserrano.characters.data.models.view.CharactersView
import com.miguelzaragozaserrano.characters.domain.repository.CharactersRepository
import com.miguelzaragozaserrano.core.base.BaseUseCase
import com.miguelzaragozaserrano.core.extensions.orEmpty
import com.miguelzaragozaserrano.core.functional.State

class CharactersUseCase(private val repository: CharactersRepository) :
    BaseUseCase<State<CharactersView>, CharactersUseCase.Params>() {

    override fun run(params: Params?) = repository.getCharacters(params?.fromPagination.orEmpty())

    data class Params(val fromPagination: Boolean)
}
