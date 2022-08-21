package com.miguelzaragozaserrano.marvel.characters

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.miguelzaragozaserrano.domain.utils.extensions.then
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.base.BaseFragment
import com.miguelzaragozaserrano.marvel.characters.TYPE.ALL
import com.miguelzaragozaserrano.marvel.characters.TYPE.FAVORITE
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
                    executeGetCharacters(true)
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
        setupToolbar(
            toolbar = mBinding.toolbarComponent.toolbar,
            titleId = R.string.title_app,
            menuId = R.menu.characters_menu,
            navigationIdIcon = null
        )
        executeGetCharacters()
    }

    override fun toolbarItemSelected(itemSelected: MenuItem, menu: Menu) {
        super.toolbarItemSelected(itemSelected, menu)
        when (itemSelected.itemId) {
            R.id.fav_icon -> {
                menu.findItem(R.id.fav_icon).icon = when (mAdapter.type) {
                    ALL -> {
                        AppCompatResources.getDrawable(requireContext(),
                            R.drawable.ic_favorite_on)
                    }
                    FAVORITE -> {
                        AppCompatResources.getDrawable(requireContext(),
                            R.drawable.ic_favorite_off)
                    }
                }
                (mAdapter.type == FAVORITE) then ALL ?: FAVORITE
            }
        }
    }

    private fun executeGetCharacters(fromPagination: Boolean = false) {
        mViewModel.executeGetCharacters(fromPagination)
    }

    private fun onCharactersLoaded(success: Any?) {
        if (success != null) {
            hideProgressDialog()
            val results = (success as CharactersView).results
            if (!mViewModel.getListCharacters().containsAll(results)) {
                rvEndListener = true
                mViewModel.addCharacters(results)
                mAdapter.collection = mViewModel.getListCharacters()
            }
        }
    }

    private fun onCharacterDetails(character: CharacterView) {
        val directions =
            CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(character)
        findNavController().navigate(directions)
    }

}