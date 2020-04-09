package com.god.taeiim.movieapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

@BindingAdapter("loadPosterImg")
fun ImageView.loadPosterImg(url: String?) {
    Glide.with(context)
        .load("https://image.tmdb.org/t/p/w300$url")
        .transform(RoundedCorners(8))
        .into(this)
}
