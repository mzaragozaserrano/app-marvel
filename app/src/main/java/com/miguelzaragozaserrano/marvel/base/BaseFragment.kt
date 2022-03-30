package com.miguelzaragozaserrano.marvel.base

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
        initSetup()
        initState()
    }

    private fun initSetup() {
        setupObservers()
        setupListeners()
        setupTextWatcher()
        setupVars()
        setupInitFunctions()
    }

    private fun initState() {
        onStateLoading()
        onStateLoaded(null)
        onStateError(null)
    }

    open fun setupObservers() {}
    open fun setupListeners() {}
    open fun setupTextWatcher() {}
    open fun setupVars() {}
    open fun setupInitFunctions() {}

    open fun onStateLoading() {}
    open fun onStateLoaded(success: Any?) {}
    open fun onStateError(message: String?) {}

    open fun onBackPressed() {}

    private fun onBackPressedDispatcher(lifecycleOwner: LifecycleOwner) {
        requireActivity().onBackPressedDispatcher
            .addCallback(lifecycleOwner, callback)
    }

}