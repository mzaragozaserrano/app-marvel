package com.miguelzaragozaserrano.marvel.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharactersBinding
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.utils.extensions.collect
import com.miguelzaragozaserrano.marvel.utils.extensions.endless
import com.miguelzaragozaserrano.marvel.utils.extensions.hideProgressDialog
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private var rvEndListener = true

    private val mViewModel: CharactersViewModel by viewModels()
    private val mBinding by viewBinding(FragmentCharactersBinding::bind)

    private val mAdapter: CharactersAdapter by lazy {
        CharactersAdapter(OnShowDetails { character ->
            onCharacterDetails(character)
        })
    }

    override fun setup1Observers() {
        super.setup1Observers()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            collect(mViewModel.charactersState, ::onCharactersLoaded)
        }
    }

    override fun setup2Listeners() {
        super.setup2Listeners()
        with(mBinding) {
            listCharacters.apply {
                endless(mAdapter.itemCount) {
                    rvEndListener = false
                    mViewModel.executeGetCharacters(true)
                }
            }
        }
    }

    override fun setup3Vars() {
        super.setup3Vars()
        with(mBinding) {
            listCharacters.adapter = mAdapter
        }
    }

    override fun setup4InitFunctions() {
        super.setup4InitFunctions()
        executeGetCharacters()
    }

    private fun executeGetCharacters() {
        mViewModel.executeGetCharacters()
    }

    private fun onCharactersLoaded(success: Any?) {
        if (success != null) {
            hideProgressDialog()
            val results = (success as CharactersView).results
            if (!mAdapter.collection.containsAll(results)) {
                rvEndListener = true
                mAdapter.collection.toMutableList().run {
                    addAll(results)
                    mAdapter.collection = this
                }
            }
        }
    }

    private fun onCharacterDetails(character: CharacterView) {
        val directions =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(character)
        findNavController().navigate(directions)
    }

}