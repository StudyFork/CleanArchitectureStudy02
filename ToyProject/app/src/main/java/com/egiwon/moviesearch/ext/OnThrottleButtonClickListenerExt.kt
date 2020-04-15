package com.egiwon.moviesearch.ext

import android.view.View
import androidx.databinding.BindingAdapter

private typealias OnClickListener = (View) -> Unit

@BindingAdapter("onSingleClick")
fun View.onSingleClick(listener: View.OnClickListener) {
    setOnClickListener(OnSingleClickListener {
        run(listener::onClick)
    })
}

class OnSingleClickListener(private val listener: OnClickListener) : View.OnClickListener {

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (now - lastTime < INTERVAL) return
        lastTime = now
        if (v != null) {
            listener(v)
        }
    }

    companion object {

        private const val INTERVAL: Long = 300

        private var lastTime: Long = 0
    }
}