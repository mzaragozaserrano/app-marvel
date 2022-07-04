package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.utils.*
import com.miguelzaragozaserrano.data.utils.Error.*
import com.miguelzaragozaserrano.data.utils.Error.Throwable
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.*
import kotlinx.coroutines.flow.*

suspend fun <T : Any, L : StateFlow<T>> BaseFragment.collect(
    flow: L,
    loaded: (Any?) -> Unit,
    error: (() -> Unit)? = null,
    loading: (() -> Unit)? = null
) {
    flow.collect {
        val state = (it as UiState<*, *>)
        when (state.status) {
            LOADING -> {
                showProgressDialog()
                loading?.invoke()
            }
            LOADED -> {
                loaded.invoke(state.success)
            }
            ERROR -> {
                hideProgressDialog()
                when (state.error) {
                    is Server -> onStateError(requireContext().getString(R.string.server_error))
                    is Unknown -> onStateError((state.error as Unknown).message)
                    is Connectivity -> onStateError((state.error as Connectivity).message)
                    is Throwable -> onStateError((state.error as Throwable).throwable?.message)
                }
                error?.invoke()
            }
        }
    }
}

suspend fun <T : Any, G : Any> Flow<Result<T>>.update(
    _state: MutableStateFlow<UiState<T, G>>,
    onSuccess: (Success<T>) -> Unit,
) {
    onStart { _state.update { it.copy(status = LOADING) } }
        .onCompletion { _state.update { it.copy(status = LOADED) } }
        .catch { throwable ->
            _state.update {
                UiState(
                    status = ERROR,
                    error = Unknown(throwable.message)
                )
            }
        }
        .collect { either ->
            either.onFailure { failure ->
                _state.update {
                    it.copy(
                        status = ERROR,
                        error = failure.error
                    )
                }
            }
            either.onSuccess { success ->
                onSuccess.invoke(success)
            }
        }
}