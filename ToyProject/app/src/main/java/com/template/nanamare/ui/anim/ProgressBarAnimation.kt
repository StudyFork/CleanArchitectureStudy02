package com.template.nanamare.ui.anim

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar

class ProgressBarAnimation(
    private val progressBar: ProgressBar,
    private val from: Int,
    private val to: Int
) : Animation() {

    init {
        duration = 1000
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        progressBar.progress = (from + ((to - from) * interpolatedTime).toInt())
    }

}