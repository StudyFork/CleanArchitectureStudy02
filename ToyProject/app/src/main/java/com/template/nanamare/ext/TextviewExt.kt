package com.template.nanamare.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.template.nanamare.anim.TextViewIntAnimation

@BindingAdapter(value = ["animInt"])
fun TextView.setAnimInt(value: Int) {
    startAnimation(TextViewIntAnimation(this, to = value))
}