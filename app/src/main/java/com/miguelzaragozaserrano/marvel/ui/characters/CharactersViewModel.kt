package com.miguelzaragozaserrano.marvel.ui.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCase
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.characters.utils.Error
import com.miguelzaragozaserrano.core.base.BaseViewModel
import com.miguelzaragozaserrano.core.extensions.cancelIfActive
import com.miguelzaragozaserrano.core.extensions.onFailure
import com.miguelzaragozaserrano.core.extensions.onSuccess
import com.miguelzaragozaserrano.marvel.data.states.CharactersState
import com.miguelzaragozaserrano.marvel.data.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersUseCase: @JvmSuppressWildcards CharactersUseCase) :
    BaseViewModel() {

    private var getCharactersJob: Job? = null

    private var _charactersState = MutableStateFlow(CharactersState())
    val charactersState get() = _charactersState.asStateFlow()

    fun getCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            charactersUseCase(CharactersUseCaseImpl.Params(fromPagination))
                .onStart { _charactersState.update { it.copy(status = Status.LOADING) } }
                .onCompletion { _charactersState.update { it.copy(status = Status.LOADED) } }
                .catch { throwable ->
                    _charactersState.update {
                        CharactersState(
                            status = Status.ERROR,
                            error = Error.Unknown(throwable.message)
                        )
                    }
                }
                .collect { either ->
                    either.onFailure { failure ->
                        _charactersState.update {
                            it.copy(
                                status = Status.ERROR,
                                error = failure
                            )
                        }
                    }
                    either.onSuccess { characters ->
                        _charactersState.update {
                            it.copy(
                                status = Status.LOADED,
                                success = characters.toCharacters().toCharactersView()
                            )
                        }
                    }
                }
        }
    }

}