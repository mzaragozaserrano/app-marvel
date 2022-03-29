package com.miguelzaragozaserrano.marvel.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.miguelzaragozaserrano.core.base.BaseFragment
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.data.utils.Status
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharactersBinding
import com.miguelzaragozaserrano.marvel.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()

    override fun setup1Observers() {
        super.setup1Observers()
        lifecycleScope.launchWhenStarted {
            viewModel.charactersState.collect { state ->
                when (state.status) {
                    Status.LOADING -> {

                    }
                    Status.LOADED -> {
                        Log.d("hola", state.success.toString())
                    }
                    Status.ERROR -> {

                    }
                }
            }
        }
    }

    override fun setup5InitFunctions() {
        super.setup5InitFunctions()
        viewModel.getCharacters(false)
    }

}