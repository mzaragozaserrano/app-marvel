package com.miguelzaragozaserrano.marvel.character

import androidx.lifecycle.viewModelScope
import com.miguelzaragozaserrano.domain.usecases.UpdateCharacter
import com.miguelzaragozaserrano.domain.usecases.UpdateCharacterUseCaseImpl
import com.miguelzaragozaserrano.marvel.base.BaseViewModel
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.LOADED
import com.miguelzaragozaserrano.marvel.utils.extensions.cancelIfActive
import com.miguelzaragozaserrano.marvel.utils.extensions.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val updateCharacter: @JvmSuppressWildcards UpdateCharacter,
) : BaseViewModel() {

    private var getCharacterJob: Job? = null

    private var _favoriteState = MutableStateFlow(UiState<Any, Any>())
    val favoriteState get() = _favoriteState.asStateFlow()

    fun executeUpdateCharacter(id: Int, status: Boolean) {
        getCharacterJob.cancelIfActive()
        getCharacterJob = viewModelScope.launch {
            updateCharacter(UpdateCharacterUseCaseImpl.Params(id, status))
                .update(_favoriteState) { state ->
                    _favoriteState.update {
                        it.copy(
                            status = LOADED,
                            success = state.data
                        )
                    }
                }
        }
    }

}