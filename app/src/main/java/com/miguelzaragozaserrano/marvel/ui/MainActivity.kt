package com.miguelzaragozaserrano.marvel.ui

import com.miguelzaragozaserrano.marvel.databinding.ActivityMainBinding
import com.miguelzaragozaserrano.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}