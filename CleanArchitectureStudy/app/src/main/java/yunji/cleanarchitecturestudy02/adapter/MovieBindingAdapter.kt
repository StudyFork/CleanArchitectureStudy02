package yunji.cleanarchitecturestudy02.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import yunji.cleanarchitecturestudy02.POSTER_URL
import yunji.cleanarchitecturestudy02.base.loadImageUrl

/*
 * Created by yunji on 10/03/2020
 */
@BindingAdapter("loadPoster")
fun loadPosterImage(imageView: ImageView, posterUrl: String) {
    loadImageUrl(imageView, POSTER_URL + posterUrl)
}