package com.miguelzaragozaserrano.marvel.ui.characters

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCase
import com.miguelzaragozaserrano.characters.domain.usecases.CharactersUseCaseImpl
import com.miguelzaragozaserrano.core.base.BaseViewModel
import com.miguelzaragozaserrano.core.extensions.cancelIfActive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val charactersUseCase: @JvmSuppressWildcards CharactersUseCase) :
    BaseViewModel() {

    private var getCharactersJob: Job? = null

    fun getCharacters(fromPagination: Boolean = false) {
        getCharactersJob.cancelIfActive()
        getCharactersJob = viewModelScope.launch {
            charactersUseCase(CharactersUseCaseImpl.Params(fromPagination))
                .onStart { }
                .onCompletion { }
                .catch { }
                .collect { result ->
                    when (result) {

                    }
                }
        }
    }

}