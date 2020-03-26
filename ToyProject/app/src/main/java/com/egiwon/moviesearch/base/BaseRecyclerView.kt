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
import io.reactivex.disposables.CompositeDisposable

typealias onItemClick<T> = ((T)) -> Unit

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<VDB : ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes resourceId: Int,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(resourceId, parent, false)
    ) {

        protected val binding: VDB = DataBindingUtil.bind(itemView)!!

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

    abstract class BaseAdapter<IDENTIFIER : BaseIdentifier, VDB : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingId: Int
    ) : PagedListAdapter<IDENTIFIER, BaseViewHolder<VDB>>(object :
        DiffUtil.ItemCallback<IDENTIFIER>() {
        override fun areItemsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IDENTIFIER, newItem: IDENTIFIER): Boolean {
            return oldItem == newItem
        }

    }) {

        protected val compositeDisposable = CompositeDisposable()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VDB> =
            object : BaseViewHolder<VDB>(parent, layoutResId, bindingId) {}

        override fun onBindViewHolder(holder: BaseViewHolder<VDB>, position: Int) =
            holder.onBindViewHolder(getItem(position))

        override fun onViewRecycled(holder: BaseViewHolder<VDB>) {
            super.onViewRecycled(holder)
            holder.onRecycledViewHolder()
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            compositeDisposable.dispose()
        }

        fun replaceAll(items: PagedList<IDENTIFIER>?) {
            if (items != null) {
                submitList(items)
            }

            notifyDataSetChanged()
        }

    }
}