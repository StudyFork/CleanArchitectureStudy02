package com.god.taeiim.movieapp.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseRecyclerViewHolder<B : ViewDataBinding>(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup,
    private val bindingVariableId: Int?
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    private val binding: B = DataBindingUtil.bind(itemView)!!

    fun onBindViewHolder(item: Any?) {
        if (bindingVariableId == null)
            return

        binding.run {
            setVariable(bindingVariableId, item)
            executePendingBindings()
        }
    }

}
