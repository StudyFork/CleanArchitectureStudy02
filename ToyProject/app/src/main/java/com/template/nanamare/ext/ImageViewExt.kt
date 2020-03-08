package com.template.nanamare.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.template.nanamare.BuildConfig
import com.template.nanamare.ui.anim.RotateTransformation

@BindingAdapter(value = ["loadUrl"])
fun ImageView.loadUrl(url: String?) {
    url?.let {
        Glide.with(this)
                .load(it)
                .into(this)
    }
}

@BindingAdapter(value = ["drawableResId"])
fun ImageView.setDrawableResId(resId: Int) {
    setImageResource(resId)
}


@BindingAdapter(value = ["loadPoster"])
fun ImageView.loadPoster(posterPath: String?) {
    posterPath ?: return
    loadUrl("${BuildConfig.IMG_URL}$posterPath")
}

fun ImageView.rotateImage(url: String?) {
    url?.let {
        Glide.with(this)
                .load(it)
                .apply(RequestOptions.bitmapTransform(
                    RotateTransformation(
                        90F
                    )
                ))
                .into(this)
    }
}

fun ImageView.loadUrlWithoutCache(url: String?) {
    url?.let {
        Glide.with(this)
                .load(it)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .apply(RequestOptions.skipMemoryCacheOf(true))
                .into(this)
    }
}
