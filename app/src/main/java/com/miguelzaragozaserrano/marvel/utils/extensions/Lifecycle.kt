package com.miguelzaragozaserrano.marvel.utils.extensions

import com.miguelzaragozaserrano.data.utils.Failure
import com.miguelzaragozaserrano.marvel.models.State
import com.miguelzaragozaserrano.marvel.utils.Status
import kotlinx.coroutines.flow.StateFlow

suspend fun <T : Any, L : StateFlow<T>> collect(
    flow: L,
    loading: () -> Unit,
    loaded: (Any?) -> Unit,
    error: (String?) -> Unit
) {
    flow.collect {
        val state = (it as State<*>)
        when (state.status) {
            Status.LOADING -> {
                loading.invoke()
            }
            Status.LOADED -> {
                loaded.invoke(state.success)
            }
            Status.ERROR -> {
                when (state.error) {
                    is Failure.Connectivity -> error.invoke("Error en la conexión")
                    is Failure.Server -> error.invoke("Error en el servidor")
                    is Failure.Unknown -> error.invoke((state.error as Failure.Unknown).message)
                    is Failure.Throwable -> error.invoke((state.error as Failure.Throwable).throwable?.message)
                }
            }
        }
    }
}