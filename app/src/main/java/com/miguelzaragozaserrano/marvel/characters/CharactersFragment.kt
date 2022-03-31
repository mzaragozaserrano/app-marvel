package com.miguelzaragozaserrano.marvel.characters

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharactersBinding
import com.miguelzaragozaserrano.marvel.models.CharactersView
import com.miguelzaragozaserrano.marvel.utils.extensions.*
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment(R.layout.fragment_characters) {

    private var rvEndListener = true

    private val mViewModel: CharactersViewModel by viewModels()
    private val mBinding by viewBinding(FragmentCharactersBinding::bind)

    private val mAdapter: CharactersAdapter by lazy {
        CharactersAdapter(OnShowDetails {

        })
    }

    override fun setup1Observers() {
        super.setup1Observers()
        lifecycleScope.launchWhenStarted {
            collect(
                mViewModel.charactersState,
                ::onStateLoading,
                ::onStateLoaded,
                ::onStateError
            )
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
        onStateLoading()
    }

    override fun onStateLoading() {
        showProgressDialog()
        mViewModel.executeGetCharacters()
    }

    override fun onStateLoaded(success: Any?) {
        if (success != null) {
            hideProgressDialog()
            rvEndListener = true
            mAdapter.collection.toMutableList().run {
                addAll((success as CharactersView).results)
                mAdapter.collection = this
            }
        }
    }

    override fun onStateError(message: String?) {
        if (message != null) {
            hideProgressDialog()
            view?.let { snackBarLong(it, message) }
        }
    }

}