package com.miguelzaragozaserrano.marvel.utils

import android.content.Context
import com.miguelzaragozaserrano.data.utils.CodeError
import com.miguelzaragozaserrano.data.utils.CodeError.*
import com.miguelzaragozaserrano.marvel.R

fun getMessage(context: Context, code: CodeError): String {
    return when (code) {
        NO_CONNECTIVITY -> context.getString(R.string.error_no_connectivity)
        NO_RESULTS -> context.getString(R.string.error_no_results)
        SERVER_ERROR -> context.getString(R.string.error_server)
        else -> context.getString(R.string.error_generic)
    }
}