package com.carlos.news

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

fun scaleView(v: View, startScale: Float, endScale: Float) {
    val anim: Animation = ScaleAnimation(
        1f, 1f,  // Start and end values for the X axis scaling
        startScale, endScale,  // Start and end values for the Y axis scaling
        Animation.RELATIVE_TO_SELF, 1f,  // Pivot point of X scaling
        Animation.RELATIVE_TO_SELF, 0f
    ) // Pivot point of Y scaling
    anim.fillAfter = true // Needed to keep the result of the animation
    anim.duration = 1000
    v.startAnimation(anim)
    v.visibility = View.VISIBLE
}