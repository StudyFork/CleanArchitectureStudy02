package com.egiwon.moviesearch.wrapper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object GlideWrapper {

    fun asyncLoadImage(target: ImageView, url: String?) {
        Glide.with(target)
            .load("$POSTER_BASE_URL$url")
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(target)
    }

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
}