package com.egiwon.moviesearch.wrapper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object GlideWrapper {
    private fun asyncLoadImage(
        target: ImageView,
        url: String?,
        block: RequestOptions.() -> RequestOptions
    ) {
        val options = RequestOptions()
        options.block()

        Glide.with(target)
            .load("$POSTER_BASE_URL$url")
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(target)
    }


    fun asyncLoadImage(target: ImageView, url: String?) {
        asyncLoadImage(target, url) {
            RequestOptions()
        }
    }

    fun asyncLoadCircleImage(target: ImageView, url: String?) {
        asyncLoadImage(target, url) {
            RequestOptions.centerCropTransform()
        }
    }

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2/"
}