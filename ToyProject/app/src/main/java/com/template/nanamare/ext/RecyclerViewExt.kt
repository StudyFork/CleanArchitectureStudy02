package com.template.nanamare.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.template.nanamare.base.ui.BaseRecyclerViewAdapter
import com.template.nanamare.base.ui.SimpleRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? SimpleRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
    (this.adapter as? BaseRecyclerViewAdapter<Any>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}
