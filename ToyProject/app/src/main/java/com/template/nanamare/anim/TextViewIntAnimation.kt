package com.template.nanamare.anim

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.TextView
import java.text.DecimalFormat


val integerCommaFormat = DecimalFormat("#,###")

class TextViewIntAnimation(
    private val textView: TextView,
    private val from: Int = 0,
    private val to: Int
) : Animation() {

    init {
        duration = 1000
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val newTime = interpolatedTime.toDouble()
        textView.text = integerCommaFormat.format((from + ((to - from) * newTime).toInt()))
    }

}