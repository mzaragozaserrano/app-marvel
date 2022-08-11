package com.miguelzaragozaserrano.marvel.utils.extensions

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.miguelzaragozaserrano.marvel.R

fun MenuItem.bindSearch(
    menu: Menu?,
    context: Context,
    onQueryChanged: (query: String) -> Unit
) {
    val searchView = actionView as SearchView
    expandActionView()
    searchView.isIconified = false
/*
    searchView.setQuery(searchViewState.query, true)
*/
    /*    adapter.setList(searchViewState.query)
        menu?.let { setItemsVisibility(it, false) }*/
    with(searchView) {
        queryHint = context.getString(R.string.hint_query_search)
        maxWidth = Integer.MAX_VALUE
        setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(auxQuery: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    /*  adapter.setList(query)
                      searchViewState.query = query*/
                    query?.let {
                        onQueryChanged.invoke(it)
                    }
                    return true
                }
            })
    }
    setOnActionExpandListener(
        object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                /*searchViewState.focus = true
                menu?.let { setItemsVisibility(it, false) }*/
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                /* searchViewState.focus = false
                 menu?.let { setItemsVisibility(it, true) }*/
                return true
            }
        }
    )
}