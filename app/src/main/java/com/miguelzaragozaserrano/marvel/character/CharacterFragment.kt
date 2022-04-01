package com.miguelzaragozaserrano.marvel.character

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharacterBinding
import com.miguelzaragozaserrano.marvel.utils.extensions.loadFromUrl
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding

class CharacterFragment : BaseFragment(R.layout.fragment_character) {

    private val arguments by navArgs<CharacterFragmentArgs>()
    private val mBinding by viewBinding(FragmentCharacterBinding::bind)

    private val character by lazy { arguments.character }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController().popBackStack()
    }

    override fun setup3Vars() {
        super.setup3Vars()
        with(mBinding) {
            titleCharacter.text = character?.name
            descriptionCharacter.text = character?.description
            headerCharacter.loadFromUrl(character?.image.orEmpty())
        }
    }

}