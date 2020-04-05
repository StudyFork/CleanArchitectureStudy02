package com.example.movieapplication.bindingdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapplication.R

@BindingAdapter("bind:setWidth")
fun ImageView.setLayoutWidth(imageWidth: Int) {
    layoutParams = layoutParams.apply {
        width = imageWidth
    }
}

@BindingAdapter("bind:setHeight")
fun ImageView.setLayoutHeight(imageHeight: Int) {
    layoutParams = layoutParams.apply {
        height = imageHeight
    }
}

@BindingAdapter("bind:setImageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.place_holder)
        .error(R.drawable.error_holder)
        .into(this)
}