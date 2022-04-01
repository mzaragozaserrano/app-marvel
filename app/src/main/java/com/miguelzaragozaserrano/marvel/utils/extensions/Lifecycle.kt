package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.utils.*
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status.*
import kotlinx.coroutines.flow.*

suspend fun <T : Any, L : StateFlow<T>> collect(
    flow: L,
    loading: () -> Unit,
    loaded: (Any?) -> Unit,
    error: (String?) -> Unit
) {
    flow.collect {
        val state = (it as UiState<*, *>)
        when (state.status) {
            LOADING -> {
                loading.invoke()
            }
            LOADED -> {
                loaded.invoke(state.success)
            }
            ERROR -> {
                when (state.error) {
                    is Error.Connectivity -> error.invoke("Error en la conexión")
                    is Error.Server -> error.invoke("Error en el servidor")
                    is Error.Unknown -> error.invoke((state.error as Error.Unknown).message)
                    is Error.Throwable -> error.invoke((state.error as Error.Throwable).throwable?.message)
                }
            }
        }
    }
}

suspend fun <T : Any, G: Any> Flow<Result<T>>.update(
    _state: MutableStateFlow<UiState<T, G>>,
    onSuccess: (Success<T>) -> Unit
) {
    onStart { _state.update { it.copy(status = Status.LOADING) } }
        .onCompletion { _state.update { it.copy(status = Status.LOADED) } }
        .catch { throwable ->
            _state.update {
                UiState(
                    status = Status.ERROR,
                    error = Error.Unknown(throwable.message)
                )
            }
        }
        .collect { either ->
            either.onFailure { failure ->
                _state.update {
                    it.copy(
                        status = Status.ERROR,
                        error = failure.error
                    )
                }
            }
            either.onSuccess { success ->
                onSuccess.invoke(success)
            }
        }
}