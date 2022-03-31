package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.utils.*
import com.miguelzaragozaserrano.marvel.models.UiState
import com.miguelzaragozaserrano.marvel.utils.Status
import kotlinx.coroutines.flow.StateFlow

suspend fun <T : Any, L : StateFlow<T>> collect(
    flow: L,
    loading: () -> Unit,
    loaded: (Any?) -> Unit,
    error: (String?) -> Unit
) {
    flow.collect {
        val state = (it as UiState<*, *>)
        when (state.status) {
            Status.LOADING -> {
                loading.invoke()
            }
            Status.LOADED -> {
                loaded.invoke(state.success)
            }
            Status.ERROR -> {
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