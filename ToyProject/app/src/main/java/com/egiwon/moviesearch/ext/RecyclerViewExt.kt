package com.egiwon.moviesearch.ext

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.moviesearch.base.BaseIdentifier
import com.egiwon.moviesearch.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun RecyclerView.replaceItems(items: PagedList<BaseIdentifier>?) {
    (adapter as? BaseRecyclerView.BaseAdapter<BaseIdentifier, *>)?.run {
        replaceAll(items)
    }
}