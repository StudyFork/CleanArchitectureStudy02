package com.egiwon.moviesearch.wrapper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.egiwon.moviesearch.R

object GlideWrapper {
    private fun asyncLoadImage(
        target: ImageView,
        url: String?,
        block: RequestOptions.() -> RequestOptions
    ) {

        Glide.with(target)
            .load("$POSTER_BASE_URL$url")
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions().block())
            .into(target)
    }


    fun asyncLoadImage(target: ImageView, url: String?) {
        asyncLoadImage(target, url) {
            RequestOptions()
        }
    }

    fun asyncLoadCircleImage(target: ImageView, url: String?) {
        asyncLoadImage(target, url) {
            RequestOptions
                .circleCropTransform()
                .placeholder(R.drawable.ic_person_outline_24px)
        }
    }

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
}