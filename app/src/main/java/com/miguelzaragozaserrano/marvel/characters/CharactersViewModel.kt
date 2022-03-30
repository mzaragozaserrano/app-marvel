package com.miguelzaragozaserrano.marvel.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.data.utils.Error
import com.miguelzaragozaserrano.data.utils.onFailure
import com.miguelzaragozaserrano.data.utils.onSuccess
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCase
import com.miguelzaragozaserrano.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.marvel.base.BaseViewModel
import com.miguelzaragozaserrano.marvel.models.CharactersState
import com.miguelzaragozaserrano.marvel.utils.Status
import com.miguelzaragozaserrano.marvel.utils.extensions.cancelIfActive
import com.miguelzaragozaserrano.marvel.utils.extensions.toCharactersView
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
                                success = characters.toCharactersView()
                            )
                        }
                    }
                }
        }
    }

}