package com.miguelzaragozaserrano.marvel.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.domain.usecases.CharactersByNameUseCaseImpl
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.domain.usecases.FindCharactersByName
import com.miguelzaragozaserrano.domain.usecases.GetCharacters
import com.miguelzaragozaserrano.marvel.base.BaseViewModel
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.LOADED
import com.miguelzaragozaserrano.marvel.utils.extensions.cancelIfActive
import com.miguelzaragozaserrano.marvel.utils.extensions.toCharactersView
import com.miguelzaragozaserrano.marvel.utils.extensions.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: @JvmSuppressWildcards GetCharacters,
    private val findCharactersByName: @JvmSuppressWildcards FindCharactersByName
) : BaseViewModel() {

    private var offset = 0
    private var getCharactersJob: Job? = null
    private val listCharacters: MutableList<CharacterView> = mutableListOf()

    private var _charactersState = MutableStateFlow(UiState<Characters, CharactersView>())
    val charactersState get() = _charactersState.asStateFlow()

    private var _findCharactersState = MutableStateFlow(UiState<Characters, CharactersView>())
    val findCharactersState get() = _findCharactersState.asStateFlow()

    fun addCharacters(list: MutableList<CharacterView>) {
        listCharacters.addAll(list)
    }

    fun getListCharacters() = listCharacters

    fun executeGetCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(CharactersUseCaseImpl.Params(fromPagination, offset))
                .update(_charactersState) { state ->
                    _charactersState.update {
                        state.data.results?.size?.let { newOffset ->
                            offset += newOffset
                        }
                        it.copy(
                            status = LOADED,
                            success = state.data.toCharactersView()
                        )
                    }
                }
        }
    }

    fun executeFindCharactersByName(query: String = "") {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            findCharactersByName(CharactersByNameUseCaseImpl.Params(query))
                .update(_findCharactersState) { state ->
                    _findCharactersState.update {
                        it.copy(
                            status = LOADED,
                            success = state.data.toCharactersView()
                        )
                    }
                }
        }
    }

}