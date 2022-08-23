package com.miguelzaragozaserrano.marvel.base

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.miguelzaragozaserrano.marvel.utils.extensions.setSupportActionBar
import com.miguelzaragozaserrano.marvel.utils.extensions.snackBarLong
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseFragment(layout: Int) : Fragment(), CoroutineScope by MainScope() {

    private val layoutID = layout

    private var menuId: Int = 0
    private lateinit var menu: Menu
    private var toolbar: Toolbar? = null
    private var functionOnCreateOptionsMenu: (() -> Unit)? = null

    var callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layoutID, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetup()
        onBackPressedDispatcher(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menuId != 0) {
            inflater.inflate(menuId, menu)
        }
        this.menu = menu
        functionOnCreateOptionsMenu?.invoke()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        toolbarItemSelected(itemSelected = item, menu = menu)
        return true
    }

    open fun setup1Observers() {}
    open fun setup2Listeners() {}
    open fun setup3Vars() {}
    open fun setup4InitFunctions() {}

    open fun onStateError(message: String?) {
        if (message != null) {
            view?.let { snackBarLong(it, message) }
        }
    }

    open fun onBackPressed() {
        findNavController().popBackStack()
    }
    open fun toolbarItemSelected(itemSelected: MenuItem, menu: Menu) {}

    fun setupToolbar(
        toolbar: Toolbar,
        titleId: Int,
        menuId: Int?,
        navigationIdIcon: Int? = null,
        functionOnCreateOptionsMenu: ((menu: Menu) -> Unit)? = null
    ) {
        setSupportActionBar(toolbar)
        with(toolbar) {
            if (navigationIdIcon != null) {
                setNavigationIcon(navigationIdIcon)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
        toolbar.title = getString(titleId)
        this.toolbar = toolbar
        if (menuId != null) {
            this.menuId = menuId
        }
        if(functionOnCreateOptionsMenu != null){
            this.functionOnCreateOptionsMenu = { functionOnCreateOptionsMenu(menu) }
        }
    }

    private fun onBackPressedDispatcher(lifecycleOwner: LifecycleOwner) {
        requireActivity().onBackPressedDispatcher
            .addCallback(lifecycleOwner, callback)
    }

    private fun initSetup() {
        setup1Observers()
        setup2Listeners()
        setup3Vars()
        setup4InitFunctions()
    }

}