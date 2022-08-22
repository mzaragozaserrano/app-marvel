package com.miguelzaragozaserrano.marvel.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.data.models.response.TYPE
import com.miguelzaragozaserrano.data.models.response.TYPE.ALL
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.domain.usecases.GetCharacters
import com.miguelzaragozaserrano.domain.usecases.GetFavoriteCharacters
import com.miguelzaragozaserrano.marvel.base.BaseViewModel
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.LOADED
import com.miguelzaragozaserrano.marvel.utils.Status.LOADING
import com.miguelzaragozaserrano.marvel.utils.extensions.cancelIfActive
import com.miguelzaragozaserrano.marvel.utils.extensions.toCharactersView
import com.miguelzaragozaserrano.marvel.utils.extensions.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: @JvmSuppressWildcards GetCharacters,
    private val getFavoriteCharacters: @JvmSuppressWildcards GetFavoriteCharacters,
) : BaseViewModel() {

    private var offset = 0
    private var getCharactersJob: Job? = null
    private val listCharacters: MutableList<CharacterView> = mutableListOf()
    private val listFavoriteCharacters: MutableList<CharacterView> = mutableListOf()

    private var _charactersState =
        MutableStateFlow(UiState<Characters, CharactersView>())
    val charactersState get() = _charactersState.asStateFlow()

    fun getListCharacters() = listCharacters

    fun changeToListCharacters() {
        viewModelScope.launch {
            _charactersState.value = _charactersState.updateAndGet {
                it.copy(status = LOADING)
            }
        }
        viewModelScope.launch {
            _charactersState.value = _charactersState.updateAndGet {
                it.success?.apply {
                    state = ALL
                    results = getListCharacters()
                }
                it.copy(status = LOADED)
            }
        }
    }

    fun updateListCharacters(id: Int?) {
        val character = listCharacters.first { it.id == id }
        listCharacters.map {
            if (character.id == id) {
                it.favorite = false
            }
        }
        listFavoriteCharacters.remove(character)
    }

    fun executeGetCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(CharactersUseCaseImpl.Params(fromPagination, offset))
                .update(_charactersState) { state ->
                    _charactersState.update {
                        state.data.results?.size?.let { newOffset ->
                            offset += newOffset
                        }
                        val results = state.data.toCharactersView().results
                        if (!getListCharacters().containsAll(results)) {
                            addCharacters(results)
                        }
                        it.copy(
                            status = LOADED,
                            success = state.data.toCharactersView()
                        )
                    }
                }
        }
    }

    fun executeGetFavoriteCharacters() {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getFavoriteCharacters()
                .update(_charactersState) { state ->
                    addFavoriteCharacters(state.data.toCharactersView().results)
                    _charactersState.update {
                        it.success?.state = TYPE.FAVORITE
                        it.copy(
                            status = LOADED,
                            success = state.data.toCharactersView()
                        )
                    }
                }
        }
    }

    private fun addCharacters(list: MutableList<CharacterView>) {
        listCharacters.addAll(list)
    }

    private fun addFavoriteCharacters(list: MutableList<CharacterView>) {
        listFavoriteCharacters.clear()
        listFavoriteCharacters.addAll(list)
    }

}