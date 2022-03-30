package com.miguelzaragozaserrano.marvel.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB
    private lateinit var layoutInflater: View

    private val progressDialog: AlertDialog by lazy {
        AlertDialog.Builder(this, R.style.TransparentDialog)
            .setView(layoutInflater)
            .setCancelable(false)
            .create()
    }

    private val animation: LottieAnimationView by lazy {
        layoutInflater.findViewById(R.id.animation_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater = View.inflate(applicationContext, R.layout.progress_bar, null)
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