package com.egiwon.moviesearch.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<B : ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes resourceId: Int,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(resourceId, parent, false)
    ) {

        protected val binding: B = DataBindingUtil.bind(itemView)!!

        open fun onBindViewHolder(item: Any?) {
            if (bindingId == null) return
            if (item == null) return

            binding.run {
                setVariable(bindingId, item)
                executePendingBindings()
            }
        }

        open fun onRecycledViewHolder() = Unit
    }

    abstract class BaseAdapter<BI : BaseIdentifier, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingId: Int
    ) : PagedListAdapter<BI, BaseViewHolder<B>>(object : DiffUtil.ItemCallback<BI>() {
        override fun areItemsTheSame(oldItem: BI, newItem: BI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BI, newItem: BI): Boolean {
            return oldItem == newItem
        }

    }) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
            object : BaseViewHolder<B>(parent, layoutResId, bindingId) {}

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) =
            holder.onBindViewHolder(getItem(position))

        override fun onViewRecycled(holder: BaseViewHolder<B>) {
            super.onViewRecycled(holder)
            holder.onRecycledViewHolder()
        }

        fun replaceAll(items: PagedList<BI>?) {
            if (items != null) {
                submitList(items)
            }

            notifyDataSetChanged()
        }

    }
}