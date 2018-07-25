package com.kevin.newsapp.ui.main.player

import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Interpolator
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.ActivityMainBinding
import com.kevin.newsapp.util.extensions.dpToPx
import com.kevin.newsapp.util.extensions.update

class PlayerTouchEventListenerImpl(
        private val mBinding: ActivityMainBinding,
        private val mInterpolator: Interpolator = AccelerateDecelerateInterpolator()
): PlayerTouchEventListener {

    override fun onScale(percent: Float) {
        val deltaTopGuidePercent = (TOP_MAX - TOP_MIN) * percent
        val deltaBottomGuidePercent = (BOTTOM_MAX - BOTTOM_MIN) * percent
        val deltaStartGuidePercent = (START_MAX - START_MIN) * percent
        val deltaEndGuidePercent = (END_MAX - END_MIN) * percent

        with(mBinding) {
            onScaleInternally(topGuideline, deltaTopGuidePercent, TOP_MAX, TOP_MIN)
            onScaleInternally(bottomGuideline, deltaBottomGuidePercent, BOTTOM_MAX, BOTTOM_MIN)
            onScaleInternally(startGuideline, deltaStartGuidePercent, START_MAX, START_MIN)
            onScaleInternally(endGuideline, deltaEndGuidePercent, END_MAX, END_MIN)

            detailContainer.alpha -= percent
        }
    }

    private fun onScaleInternally(guideline: Guideline, delta: Float, MAX: Float, MIN: Float) {
        val old = (guideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val new = old + delta

        if(MAX > MIN) {
            when {
                new > MAX -> guideline.setGuidelinePercent(MAX)
                new < MIN -> guideline.setGuidelinePercent(MIN)
                else -> guideline.setGuidelinePercent(new)
            }
        } else { // end guideline
            when {
                new > MIN -> guideline.setGuidelinePercent(MIN)
                new < MAX -> guideline.setGuidelinePercent(MAX)
                else -> guideline.setGuidelinePercent(new)
            }
        }
    }

    override fun onSwipe(percent: Float) {
        with(mBinding) {
            onSwipeInternally(startGuideline, percent)
            onSwipeInternally(endGuideline, percent)
        }
    }

    private fun onSwipeInternally(guideline: Guideline, delta: Float) {
        val old = (guideline.layoutParams as ConstraintLayout.LayoutParams).guidePercent
        val new = old + delta
        guideline.setGuidelinePercent(new)
    }

    override fun onExpand() {
        with(mBinding) {
            mainParentLayout.update {
                setGuidelinePercent(R.id.topGuideline, TOP_MIN)
                setGuidelinePercent(R.id.bottomGuideline, BOTTOM_MIN)
                setGuidelinePercent(R.id.startGuideline, START_MIN)
                setGuidelinePercent(R.id.endGuideline, END_MIN)

                TransitionManager.beginDelayedTransition(mainParentLayout, ChangeBounds().apply {
                    duration = 500L
                    interpolator = mInterpolator
                })
            }

            alphaAnimation(detailContainer, true)
        }
    }

    override fun onShrink(alphaAnimate: Boolean) {
        with(mBinding) {
            mainParentLayout.update {
                setGuidelinePercent(R.id.topGuideline, TOP_MAX)
                setGuidelinePercent(R.id.bottomGuideline, BOTTOM_MAX)
                setGuidelinePercent(R.id.startGuideline, START_MAX)
                setGuidelinePercent(R.id.endGuideline, END_MAX)

                TransitionManager.beginDelayedTransition(mainParentLayout, ChangeBounds().apply {
                    duration = 500L
                    interpolator = mInterpolator
                })
            }

            if(alphaAnimate) alphaAnimation(detailContainer, false)
        }
    }

    override fun onDismiss() {
        with(mBinding) {
            mainParentLayout.update {
                setGuidelineEnd(R.id.topGuideline, dpToPx(56))
                setGuidelineEnd(R.id.bottomGuideline, dpToPx(56))

                TransitionManager.beginDelayedTransition(mainParentLayout, ChangeBounds().apply {
                    interpolator = AccelerateDecelerateInterpolator()
                    duration = 500L
                })
            }
        }
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

        const val TOP_MAX = .7f; const val TOP_MIN = .0f
        const val BOTTOM_MAX = .875f; const val BOTTOM_MIN = .4f
        const val START_MAX = .45f; const val START_MIN = .0f
        const val END_MAX = .95f; const val END_MIN = 1.0f
    }
}