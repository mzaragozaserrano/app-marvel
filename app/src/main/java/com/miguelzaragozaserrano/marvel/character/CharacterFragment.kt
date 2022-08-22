package com.miguelzaragozaserrano.marvel.character

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.miguelzaragozaserrano.data.utils.extensions.orEmpty
import com.miguelzaragozaserrano.domain.utils.extensions.orEmpty
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.databinding.FragmentCharacterBinding
import com.miguelzaragozaserrano.marvel.utils.extensions.*
import com.miguelzaragozaserrano.marvel.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : BaseFragment(R.layout.fragment_character) {

    private val arguments by navArgs<CharacterFragmentArgs>()
    private val mViewModel: CharacterViewModel by viewModels()
    private val mBinding by viewBinding(FragmentCharacterBinding::bind)

    private val character by lazy { arguments.character }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun setup1Observers() {
        super.setup1Observers()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            collect(mViewModel.favoriteState, ::onFavoriteUpdated)
        }
    }

    override fun setup2Listeners() {
        super.setup2Listeners()
        with(mBinding) {
            fab.apply {
                setOnClickListener {
                    executeUpdateCharacter()
                }
            }
        }
    }

    override fun setup3Vars() {
        super.setup3Vars()
        with(mBinding) {
            titleCharacter.text = character?.name
            descriptionCharacter.text = character?.description
            headerCharacter.loadFromUrl(character?.image.orEmpty())
            fab.bindFabIcon(character?.favorite.orEmpty())
        }
    }

    override fun setup4InitFunctions() {
        super.setup4InitFunctions()
        setupToolbar(
            toolbar = mBinding.toolbarComponent.toolbar,
            titleId = R.string.title_app,
            menuId = null,
            navigationIdIcon = R.drawable.ic_arrow_back
        )
    }

    private fun executeUpdateCharacter() {
        val status = !character?.favorite.orEmpty()
        mBinding.fab.bindFabIcon(status)
        mViewModel.executeUpdateCharacter(character?.id.orEmpty(), status)
    }

    private fun onFavoriteUpdated(success: Any?) {
        if (success != null) {
            hideProgressDialog()
            val result = success as Boolean
            if (result) {
                character?.favorite = !character?.favorite.orEmpty()
                if (character?.favorite.orEmpty()) {
                    view?.let { snackBarLong(it, "Personaje añadido a favoritos") }
                } else {
                    setFragmentResult("requestKey", bundleOf("character" to character?.id))
                    view?.let { snackBarLong(it, "Personaje eliminado de favoritos") }
                }
            }
        }
    }

}