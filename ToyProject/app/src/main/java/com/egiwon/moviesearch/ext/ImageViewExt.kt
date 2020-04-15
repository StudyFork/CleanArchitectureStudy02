package com.egiwon.moviesearch.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.egiwon.moviesearch.wrapper.GlideWrapper

@BindingAdapter("loadAsyncImage")
fun ImageView.loadAsyncImage(url: String?) = GlideWrapper.asyncLoadImage(this, url)

@BindingAdapter("loadAsyncCastImage")
fun ImageView.loadAsyncCastImage(url: String?) = GlideWrapper.asyncLoadCircleImage(this, url)

