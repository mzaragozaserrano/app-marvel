package com.miguelzaragozaserrano.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
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
        setupInit()
    }

    private fun setupInit() {
        setup1Observers()
        setup2Listeners()
        setup3TextWatcher()
        setup4Vars()
        setup5InitFunctions()
    }

    open fun setup1Observers() {}
    open fun setup2Listeners() {}
    open fun setup3TextWatcher() {}
    open fun setup4Vars() {}
    open fun setup5InitFunctions() {}

    open fun onBackPressed() {}

    private fun onBackPressedDispatcher(lifecycleOwner: LifecycleOwner) {
        requireActivity().onBackPressedDispatcher
            .addCallback(lifecycleOwner, callback)
    }

}