package com.miguelzaragozaserrano.marvel.character

import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharacterBinding
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding

class CharacterFragment : BaseFragment(R.layout.fragment_character) {
    private val mBinding by viewBinding(FragmentCharacterBinding::bind)
}