package yunji.cleanarchitecturestudy02.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/*
 * Created by yunji on 09/03/2020
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("bindItem")
fun bindItems(rv: RecyclerView, data: List<Any>?) {
    (rv.adapter as BaseRecyclerView<*, Any>).updateItems(data ?: emptyList())
}

@BindingAdapter("loadImage")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("setPaddingVertical")
fun setPaddingVertical(view: View, padding: Int) {
    view.setPadding(view.paddingLeft, padding, view.paddingRight, padding)
}

@BindingAdapter("setPaddingHorizontal")
fun setPaddingHorizontal(view: View, padding: Int) {
    view.setPadding(padding, view.paddingTop, padding, view.paddingBottom)
}