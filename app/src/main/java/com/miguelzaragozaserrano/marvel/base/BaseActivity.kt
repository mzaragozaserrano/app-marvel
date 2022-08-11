package com.miguelzaragozaserrano.marvel.base

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar
import com.miguelzaragozaserrano.marvel.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB
    private lateinit var menu: Menu
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
    }

    abstract fun getViewBinding(): VB

    fun showProgressDialog() {
        if (!progressDialog.isShowing) {
            animation.playAnimation()
            progressDialog.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog.isShowing) {
            animation.cancelAnimation()
            progressDialog.dismiss()
        }
    }

    fun snackBarLong(
        view: View,
        text: String,
        colorBackground: Int,
        colorText: Int
    ) {
        val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snackBar.view.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                colorBackground
            )
        )
        snackBar.setTextColor(ContextCompat.getColor(applicationContext, colorText))
        snackBar.show()
    }

    fun toastLong(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}