package com.egiwon.moviesearch.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.base.BasePagedAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replacePagedItems")
fun <T : BaseIdentifier> RecyclerView.replacePagedItems(items: PagedList<T>?) {
    (adapter as? BasePagedAdapter<T, *>)?.run {
        replaceAllPagedItems(items)
    }
}