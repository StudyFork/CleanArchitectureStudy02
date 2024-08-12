package com.template.nanamare.ext

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["selected"])
fun View.setSelected(selected: Boolean) {
    isSelected = selected
}