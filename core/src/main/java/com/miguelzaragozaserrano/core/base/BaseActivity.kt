package com.miguelzaragozaserrano.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupInit()
    }

    abstract fun getViewBinding(): VB

    private fun setupInit() {
        setup1Toolbar()
        setup2Menu()
        setup3Navigation()
        setup4Vars()
        setup5InitFunctions()
        setup6ClickListeners()
    }

    open fun setup1Toolbar() {}
    open fun setup2Menu() {}
    open fun setup3Navigation() {}
    open fun setup4Vars() {}
    open fun setup5InitFunctions() {}
    open fun setup6ClickListeners() {}

}