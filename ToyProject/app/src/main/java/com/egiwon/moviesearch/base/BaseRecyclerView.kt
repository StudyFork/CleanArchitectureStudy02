package com.egiwon.moviesearch.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<B : ViewDataBinding>(
        itemView: View,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(itemView) {

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

    abstract class BaseAdapter<A : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingId: Int
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        private val list = mutableListOf<A>()

        private lateinit var layoutInflater: LayoutInflater

        private fun provideLayoutInflater(context: Context): LayoutInflater {
            if (!::layoutInflater.isInitialized) layoutInflater = LayoutInflater.from(context)
            return layoutInflater
        }

        private fun onCreateViewHolder(
            inflater: LayoutInflater,
            parent: ViewGroup
        ): BaseViewHolder<B> = object :
            BaseViewHolder<B>(
                inflater.inflate(layoutResId, parent, false) as ViewGroup,
                bindingId
            ) {}

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
            onCreateViewHolder(provideLayoutInflater(parent.context), parent)

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) =
            holder.onBindViewHolder(list[position])

        protected fun getItem(position: Int): A? = list.getOrNull(position)

        fun replaceAll(items: List<A>?) {
            if (items != null) {
                list.run {
                    clear()
                    addAll(items)
                }
            }

            notifyDataSetChanged()
        }

    }
}