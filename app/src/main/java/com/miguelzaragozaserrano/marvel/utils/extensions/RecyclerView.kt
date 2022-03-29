package com.miguelzaragozaserrano.marvel.utils.extensions

import androidx.recyclerview.widget.RecyclerView
import com.miguelzaragozaserrano.marvel.utils.EndlessScroll

fun RecyclerView.endless(visibleThreshold: Int, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}