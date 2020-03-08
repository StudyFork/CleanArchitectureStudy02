package com.template.nanamare.ext

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import com.jakewharton.rxbinding3.view.clicks
import org.jetbrains.anko.backgroundResource
import java.util.concurrent.TimeUnit

@BindingAdapter(value = ["selected"])
fun View.setSelected(selected: Boolean) {
    isSelected = selected
}

@BindingAdapter(value = ["enabled"])
fun View.setEnabled(enabled: Boolean) {
    isEnabled = enabled
}

@SuppressLint("CheckResult")
@BindingAdapter(value = ["onBlockClick"])
fun View.setOnBlockClick(listener: View.OnClickListener) {
    clicks()
        .throttleFirst(1000L, TimeUnit.MILLISECONDS)
        .subscribe {
            listener.onClick(this)
        }
}

@BindingAdapter(value = ["backgroundResId"])
fun View.setOnBackgroundResId(resId: Int) {
    backgroundResource = resId
}


/**
 * 점선은 따로 View에서 hardwareAccelerated를 false로 해야 해서 BindingAdapter로 선언
 */
@BindingAdapter(value = ["hardwareAccelerated"])
fun View.setHardwareAccelerated(enabled: Boolean) {
    setLayerType(if (enabled) View.LAYER_TYPE_HARDWARE else View.LAYER_TYPE_SOFTWARE, null)
}