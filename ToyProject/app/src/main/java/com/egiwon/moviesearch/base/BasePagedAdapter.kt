package com.egiwon.moviesearch.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagedAdapter<IDENTIFIER : BaseIdentifier, VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
    private val bindingId: Int,
    private val viewModels: Map<Int?, BaseViewModel> = mapOf()
) : PagedListAdapter<IDENTIFIER, BaseViewHolder<VDB>>(
    object : DiffUtil.ItemCallback<IDENTIFIER>() {
        override fun areItemsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> =
        object : BaseViewHolder<VDB>(parent, layoutResId, bindingId, viewModels) {}

    override fun onBindViewHolder(holder: BaseViewHolder<VDB>, position: Int) =
        holder.onBindViewHolder(getItem(position))

    override fun onViewRecycled(holder: BaseViewHolder<VDB>) {
        super.onViewRecycled(holder)
        holder.onRecycledViewHolder()
    }

    fun replaceAllPagedItems(items: PagedList<IDENTIFIER>?) {
        if (items != null) {
            submitList(items)
        }
    }

}