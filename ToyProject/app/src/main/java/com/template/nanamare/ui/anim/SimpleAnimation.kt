package com.template.nanamare.ui.anim

import android.view.animation.TranslateAnimation

object SimpleAnimation {

    val rightToLeft = TranslateAnimation(
        200f,
        0f,
        0.0f,
        0.0f
    ).apply {
        duration = 1000
        fillAfter = true
    }

    val leftToRight = TranslateAnimation(
        -200f,
        0f,
        0.0f,
        0.0f
    ).apply {
        duration = 1000
        fillAfter = true
    }

    val bottomToTop = TranslateAnimation(
        0f,
        0f,
        200f,
        0f
    ).apply {
        duration = 1000
        fillAfter = true
    }

}