package com.example.toyproject.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.toyproject.data.entity.SearchMovieData
import com.example.toyproject.ui.main.MainMoviePostingRecyclerAdapter

@BindingAdapter("bind:replace")
fun RecyclerView.replaceAll(item: List<SearchMovieData>?) {
    if (!item.isNullOrEmpty()) (adapter as? MainMoviePostingRecyclerAdapter)!!.setItemList(item as ArrayList<SearchMovieData>)
}

@BindingAdapter("bind:bindImage")
fun bindImage(imageView: ImageView, imageUri: String) {
    if (imageUri.isNotEmpty())
    Glide.with(imageView.context).load(imageUri).into(imageView)
}