package com.miguelzaragozaserrano.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.miguelzaragozaserrano.marvel.utils.extensions.snackBarLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseFragment(layout: Int) : Fragment(), CoroutineScope by MainScope() {

    private val layoutID = layout

    var callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup4InitFunctions()
        onBackPressedDispatcher(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutID, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetup()
    }

    private fun initSetup() {
        setup1Observers()
        setup2Listeners()
        setup3Vars()
    }

    open fun setup1Observers() {}
    open fun setup2Listeners() {}
    open fun setup3Vars() {}
    open fun setup4InitFunctions() {}

    open fun onStateError(message: String?) {
        if (message != null) {
            view?.let { snackBarLong(it, message) }
        }
    }

    open fun onBackPressed() {}

    private fun onBackPressedDispatcher(lifecycleOwner: LifecycleOwner) {
        requireActivity().onBackPressedDispatcher
            .addCallback(lifecycleOwner, callback)
    }

}