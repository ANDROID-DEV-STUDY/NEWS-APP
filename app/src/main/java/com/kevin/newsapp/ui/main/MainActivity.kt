package com.kevin.newsapp.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.ActivityMainBinding
import com.kevin.newsapp.ui.base.BaseActivity
import com.kevin.newsapp.ui.main.discover.DiscoverFragment
import com.kevin.newsapp.ui.main.headline.HeadlineFragment
import com.kevin.newsapp.ui.main.player.PlayerTouchEventListener
import com.kevin.newsapp.ui.main.player.PlayerTouchHandler
import com.kevin.newsapp.ui.main.profile.ProfileFragment
import com.kevin.newsapp.ui.main.video.VideoFragment
import com.kevin.newsapp.util.extensions.dpToPx
import com.kevin.newsapp.util.extensions.update
import com.kevin.newsapp.view.disableShiftMode

class MainActivity:
        BaseActivity<ActivityMainBinding, MainViewModel>(),
        PlayerTouchEventListener
{

    override val layoutResID: Int
        get() = R.layout.activity_main

    override val modelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    private val headlineFragment = HeadlineFragment.newInstance()
    private val videoFragment = VideoFragment.newInstance()
    private val discoverFragment = DiscoverFragment.newInstance()
    private val profileFragment = ProfileFragment.newInstance()

    private lateinit var mActiveFragment: Fragment

    private val youtubePlayerTouchHandler = PlayerTouchHandler(this)

    private val interpolator = AccelerateDecelerateInterpolator()

    private val youtubePlayerTransition: Transition = ChangeBounds().apply {
        duration = 500
        interpolator = this@MainActivity.interpolator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding.viewModel = mViewModel

        initFragment()

        initView()
    }

    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, profileFragment, ProfileFragment.TAG)
                .hide(profileFragment)
                .commitNow()

        supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, discoverFragment, DiscoverFragment.TAG)
                .hide(discoverFragment)
                .commitNow()

        supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, videoFragment, VideoFragment.TAG)
                .hide(videoFragment)
                .commitNow()

        supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, headlineFragment, HeadlineFragment.TAG)
                .commitNow()

        mActiveFragment = headlineFragment
    }

    private fun initView() {
        with(mBinding) {
            initBottomNavigationView(mainBottomNavigator)

            initPlayerController(mainVideoContainer, youtubePlayerTouchHandler)
        }
    }

    private fun initBottomNavigationView(navigationView: BottomNavigationView) {
        navigationView.disableShiftMode()

        navigationView.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener when(item.itemId) {
                R.id.action_menu_headline -> { navigateTo(headlineFragment) }
                R.id.action_menu_video -> { navigateTo(videoFragment) }
                R.id.action_menu_discover -> { navigateTo(discoverFragment) }
                R.id.action_menu_profile -> { navigateTo(profileFragment) }
                else -> false
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPlayerController(videoContainer: FrameLayout, controller: View.OnTouchListener) {
        videoContainer.setOnTouchListener(controller)
    }

    private fun navigateTo(activeFragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().hide(mActiveFragment).show(activeFragment).commitNow()
        mActiveFragment = activeFragment
        return true
    }

    override fun onScale(percent: Float) {
        val deltaTopGuidePercent = (TOP_MAX - TOP_MIN) * percent
        val deltaBottomGuidePercent = (BOTTOM_MAX - BOTTOM_MIN) * percent
        val deltaStartGuidePercent = (START_MAX - START_MIN) * percent
        val deltaEndGuidePercent = (END_MAX - END_MIN) * percent

        with(mBinding) {
            onScaleInternally(mainTopGuideline, deltaTopGuidePercent, TOP_MAX, TOP_MIN)
            onScaleInternally(mainBottomGuideline, deltaBottomGuidePercent, BOTTOM_MAX, BOTTOM_MIN)
            onScaleInternally(mainStartGuideline, deltaStartGuidePercent, START_MAX, START_MIN)
            onScaleInternally(mainEndGuideline, deltaEndGuidePercent, END_MAX, END_MIN)

            mainDetailContainer.alpha -= percent
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
            onSwipeInternally(mainStartGuideline, percent)
            onSwipeInternally(mainEndGuideline, percent)
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
                setGuidelinePercent(R.id.mainTopGuideline, TOP_MIN)
                setGuidelinePercent(R.id.mainBottomGuideline, BOTTOM_MIN)
                setGuidelinePercent(R.id.mainStartGuideline, START_MIN)
                setGuidelinePercent(R.id.mainEndGuideline, END_MIN)
            }

            TransitionManager.beginDelayedTransition(mainParentLayout, youtubePlayerTransition)

            alphaAnimation(mainDetailContainer, true)

            youtubePlayerTouchHandler.isExpand = true
        }
    }

    private fun alphaAnimation(view: View, show: Boolean) {
        val fromAlpha = view.alpha
        val toAlpha = if(show) 1.0f else 0f

        if(fromAlpha != toAlpha) {
            view.animate()
                    .alpha(toAlpha)
                    .setDuration(500L)
                    .setInterpolator(interpolator)
                    .start()
        }
    }

    override fun onShrink(alphaAnimate: Boolean) {
        with(mBinding) {
            mainParentLayout.update {
                setGuidelinePercent(R.id.mainTopGuideline, TOP_MAX)
                setGuidelinePercent(R.id.mainBottomGuideline, BOTTOM_MAX)
                setGuidelinePercent(R.id.mainStartGuideline, START_MAX)
                setGuidelinePercent(R.id.mainEndGuideline, END_MAX)
            }

            TransitionManager.beginDelayedTransition(mainParentLayout, youtubePlayerTransition)

            if(alphaAnimate) alphaAnimation(mainDetailContainer, false)

            youtubePlayerTouchHandler.isExpand = false
        }
    }

    override fun onDismiss() {
        with(mBinding) {
            mainParentLayout.update {
                setGuidelineEnd(R.id.mainTopGuideline, dpToPx(56))
                setGuidelineEnd(R.id.mainBottomGuideline, dpToPx(56))
            }

            TransitionManager.beginDelayedTransition(mainParentLayout, youtubePlayerTransition)
        }
    }

    override fun onBackPressed() {
        if(youtubePlayerTouchHandler.isExpand) { onShrink(true) }
        else super.onBackPressed()
    }

    companion object {
        const val TOP_MAX = .7f
        const val TOP_MIN = .0f
        const val BOTTOM_MAX = .875f
        const val BOTTOM_MIN = .4f
        const val START_MAX = .45f
        const val START_MIN = .0f
        const val END_MAX = .95f
        const val END_MIN = 1.0f

        val TAG = MainActivity::class.java.simpleName
    }
}