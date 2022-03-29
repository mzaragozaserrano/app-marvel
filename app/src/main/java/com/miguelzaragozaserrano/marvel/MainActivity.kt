package com.miguelzaragozaserrano.marvel

import com.miguelzaragozaserrano.marvel.databinding.ActivityMainBinding
import com.miguelzaragozaserrano.marvel.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}