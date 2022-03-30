package com.miguelzaragozaserrano.marvel.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharactersBinding
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.utils.extensions.collect
import com.miguelzaragozaserrano.marvel.utils.extensions.hideProgressDialog
import com.miguelzaragozaserrano.marvel.utils.extensions.showProgressDialog
import com.miguelzaragozaserrano.marvel.utils.extensions.snackBarLong
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter: CharactersAdapter by lazy { CharactersAdapter() }

    override fun setupObservers() {
        super.setupObservers()
        lifecycleScope.launchWhenStarted {
            collect(
                viewModel.charactersState,
                ::onStateLoading,
                ::onStateLoaded,
                ::onStateError
            )
        }
    }

    override fun setupVars() {
        super.setupVars()
        with(binding) {
            listCharacters.adapter = adapter
        }
    }

    override fun setupInitFunctions() {
        super.setupInitFunctions()
        onStateLoading()
    }

    override fun onStateLoading() {
        showProgressDialog()
        viewModel.executeGetCharacters(false)
    }

    override fun onStateLoaded(success: Any?) {
        if (success != null) {
            hideProgressDialog()
            adapter.collection = (success as CharactersView).results
        }
    }

    override fun onStateError(message: String?) {
        if (message != null) {
            hideProgressDialog()
            view?.let { snackBarLong(it, message) }
        }
    }

}