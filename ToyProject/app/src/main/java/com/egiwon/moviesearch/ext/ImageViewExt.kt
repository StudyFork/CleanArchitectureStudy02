package com.egiwon.moviesearch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("loadAsyncImage")
fun ImageView.loadAsyncImage(url: String) {

    Glide.with(this)
        .load("$POSTER_BASE_URL$url")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"