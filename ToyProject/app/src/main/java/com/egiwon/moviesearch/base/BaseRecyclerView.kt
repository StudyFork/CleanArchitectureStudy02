package com.egiwon.moviesearch.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
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
    }

    abstract class BaseAdapter<I : BaseIdentifier, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingId: Int
    ) : ListAdapter<I, BaseViewHolder<B>>(object : DiffUtil.ItemCallback<I>() {
        override fun areItemsTheSame(oldItem: I, newItem: I): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: I, newItem: I): Boolean {
            return oldItem == newItem
        }
    }) {

        private lateinit var layoutInflater: LayoutInflater

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
            object : BaseViewHolder<B>(parent, layoutResId, bindingId) {}

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) =
            holder.onBindViewHolder(getItem(position))

        fun replaceAll(items: List<I>?) {
            if (items != null) {
                submitList(items)
            }

            notifyDataSetChanged()
        }

    }
}