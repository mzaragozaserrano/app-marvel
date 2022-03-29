package com.miguelzaragozaserrano.marvel.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharactersBinding
import com.miguelzaragozaserrano.marvel.ui.base.BaseFragment
import com.miguelzaragozaserrano.marvel.ui.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters(false)
    }

}