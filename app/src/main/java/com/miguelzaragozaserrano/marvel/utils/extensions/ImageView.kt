package com.miguelzaragozaserrano.marvel.utils.extensions

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.utils.GlideApp

fun AppCompatImageView.loadFromUrl(url: String) =
    GlideApp.with(this.context)
        .load(url)
        .timeout(60000)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.error_image)
        )
        .skipMemoryCache(true)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)