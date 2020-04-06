package com.example.movieapplication.bindingdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

@BindingAdapter("bind:setCircleImageUrl")
fun ImageView.setCircleImageUrl(url: String?) {

    val options = RequestOptions().apply {
        circleCrop()
    }

    Glide.with(context)
        .load(url)
        .apply(options)
        .placeholder(R.drawable.place_holder_circle)
        .error(R.drawable.error_holder_circle)
        .into(this)
}