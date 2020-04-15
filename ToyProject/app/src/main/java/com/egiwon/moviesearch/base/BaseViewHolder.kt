package com.egiwon.moviesearch.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VDB : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes resourceId: Int,
    private val bindingId: Int?,
    private val viewModels: Map<Int?, BaseViewModel> = mapOf()
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(resourceId, parent, false)
) {

    protected val binding: VDB = DataBindingUtil.bind(itemView)!!

    open fun onBindViewHolder(item: Any?) {
        if (bindingId == null) return
        if (item == null) return

        binding.run {
            viewModels.let {
                for (key in it.keys) {
                    if (key == null) continue
                    setVariable(key, it[key])
                }
            }

            setVariable(bindingId, item)
            executePendingBindings()
        }
    }

    open fun onRecycledViewHolder() = Unit
}