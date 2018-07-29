package com.kevin.newsapp.ui.main.player

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import com.kevin.newsapp.databinding.ActivityMainBinding

class PlayerTouchEventListenerImpl(
        private val mBinding: ActivityMainBinding,
        private val mInterpolator: Interpolator = AccelerateDecelerateInterpolator()
): PlayerTouchEventListener {

    override fun onScale(percent: Float) {

    }



    override fun onSwipe(percent: Float) {

    }

    override fun onExpand() {

    }

    override fun onShrink(alphaAnimate: Boolean) {

    }

    override fun onDismiss() {
    }

    private fun alphaAnimation(view: View, show: Boolean) {
        val fromAlpha = view.alpha
        val toAlpha = if(show) 1.0f else 0f

        if(fromAlpha != toAlpha)
            view.animate()
                    .alpha(toAlpha)
                    .setDuration(500L)
                    .setInterpolator(mInterpolator)
                    .start()
    }

    companion object {
        val TAG = PlayerTouchEventListenerImpl::class.java.simpleName
    }
}