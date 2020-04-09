package com.god.taeiim.movieapp.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseRecyclerAdapter<ITEM, B : ViewDataBinding>(
    @LayoutRes val layoutResId: Int,
    val bindingVariableId: Int? = null
) : RecyclerView.Adapter<BaseRecyclerViewHolder<B>>() {

    private val items = mutableListOf<ITEM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<B> =
        object : BaseRecyclerViewHolder<B>(
            layoutResId,
            parent,
            bindingVariableId
        ) {}

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<B>, position: Int) =
        holder.onBindViewHolder(items[position])

    protected fun getItem(position: Int): ITEM =
        items.getOrNull(position) ?: throw ArrayIndexOutOfBoundsException()

    fun updateItems(items: List<ITEM>?) {
        this.items.run {
            clear()
            items?.let {
                addAll(it)
            }
        }

        notifyDataSetChanged()
    }

    fun clearItems() {
        val size = items.size
        this.items.clear()
        notifyItemRangeRemoved(0, size)
    }

}