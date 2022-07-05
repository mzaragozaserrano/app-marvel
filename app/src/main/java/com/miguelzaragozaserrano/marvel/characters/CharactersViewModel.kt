package com.miguelzaragozaserrano.marvel.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.data.models.response.Characters
import com.miguelzaragozaserrano.domain.usecases.GetCharacters
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.marvel.base.BaseViewModel
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status
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
class CharactersViewModel @Inject constructor(private val getCharacters: @JvmSuppressWildcards GetCharacters) :
    BaseViewModel() {

    var offset = 0
    private var getCharactersJob: Job? = null

    private var _charactersState = MutableStateFlow(UiState<Characters, CharactersView>())
    val charactersState get() = _charactersState.asStateFlow()

    fun executeGetCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            getCharacters(CharactersUseCaseImpl.Params(fromPagination, offset))
                .update(_charactersState) { state ->
                    _charactersState.update {
                        offset += 20
                        it.copy(
                            status = Status.LOADED,
                            success = state.data.toCharactersView()
                        )
                    }
                }
        }
    }

}