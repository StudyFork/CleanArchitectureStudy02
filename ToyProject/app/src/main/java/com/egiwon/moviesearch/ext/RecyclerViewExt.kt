package com.egiwon.moviesearch.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun <T : BaseIdentifier> RecyclerView.replaceItems(items: PagedList<T>?) {
    (adapter as? BaseRecyclerView.BaseAdapter<T, *>)?.run {
        replaceAll(items)
    }
}