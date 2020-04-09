package com.god.taeiim.movieapp.ui

import androidx.databinding.ViewDataBinding
import com.god.taeiim.movieapp.base.BaseRecyclerAdapter

class MovieRecyclerAdapter<ITEM : Any, B : ViewDataBinding>(
    layout: Int,
    bindingVariableId: Int
) : BaseRecyclerAdapter<ITEM, B>(layout, bindingVariableId)