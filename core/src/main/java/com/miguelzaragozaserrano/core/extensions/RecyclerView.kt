package com.miguelzaragozaserrano.core.extensions

import androidx.recyclerview.widget.RecyclerView
import com.miguelzaragozaserrano.core.utils.EndlessScroll

fun RecyclerView.endless(visibleThreshold: Int, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}