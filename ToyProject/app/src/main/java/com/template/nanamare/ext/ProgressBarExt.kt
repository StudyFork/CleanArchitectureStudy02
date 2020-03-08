package com.template.nanamare.ext

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.template.nanamare.ui.anim.ProgressBarAnimation

@BindingAdapter(value = ["pbMax", "pbProgress"])
fun ProgressBar.setValue(pbMax: Int, pbProgress: Int) {
    max = pbMax
    var progress = pbProgress
    if(pbProgress in 1..(pbMax*0.08).toInt()) {
        progress = (pbMax * 0.08).toInt()
    }
    startAnimation(ProgressBarAnimation(this, 0, progress))
}