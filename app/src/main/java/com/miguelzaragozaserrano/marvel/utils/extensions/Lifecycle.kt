package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.utils.Error.*
import com.miguelzaragozaserrano.data.utils.Error.Throwable
import com.miguelzaragozaserrano.data.utils.Result
import com.miguelzaragozaserrano.data.utils.Success
import com.miguelzaragozaserrano.data.utils.onFailure
import com.miguelzaragozaserrano.data.utils.onSuccess
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.*
import com.miguelzaragozaserrano.marvel.utils.getMessage
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
                    is Connectivity -> onStateError(
                        getMessage(
                            requireContext(),
                            (state.error as Connectivity).code
                        )
                    )
                    is NoResults -> onStateError(
                        getMessage(
                            requireContext(),
                            (state.error as NoResults).code
                        )
                    )
                    is Server -> onStateError(
                        getMessage(
                            requireContext(),
                            (state.error as Server).code
                        )
                    )
                    is Throwable -> onStateError((state.error as Throwable).throwable?.message)
                    is Unknown -> onStateError(
                        getMessage(
                            requireContext(),
                            (state.error as Unknown).code
                        )
                    )
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
        .catch {
            _state.update {
                UiState(
                    status = ERROR,
                    error = Unknown()
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