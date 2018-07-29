package com.kevin.newsapp.ui.main.video

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.jakewharton.rxbinding2.view.focusChanges
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentVideoBinding
import com.kevin.newsapp.ui.base.BaseFragment
import com.kevin.newsapp.ui.main.video.adapter.VideoViewPagerAdapter
import com.kevin.newsapp.util.extensions.*
import com.kevin.newsapp.util.other.log
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class VideoFragment: BaseFragment<FragmentVideoBinding, VideoViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_video

    override val modelClass: Class<VideoViewModel>
        get() = VideoViewModel::class.java

    private val transition = ChangeBounds().apply {
        duration = 500L
        interpolator = AccelerateDecelerateInterpolator()
    }

    override fun onCreateView() {
        with(mBinding) {
            videoSearchButton.onClick { showSearchView() }

            videoSearchCancelButton.onClick { hideSearchView() }

            videoSearchEditText.focusChanges()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        with(videoSearchEditText) {
                            if(it) showKeyboard() else hideKeyboard()
                        }
                    }
                    .addTo(mDisposable)

            initTabPager()
        }
    }

    private fun initTabPager() {
        with(mBinding) {
            videoViewPager.adapter = VideoViewPagerAdapter(mContext)
            setUpTabPager(videoTabLayout, videoViewPager)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.videoViewModel = mViewModel

        mViewModel.init()

        mViewModel.videoSearchState.observe(this, Observer {
            when(it) {
                is CommonState.Init -> { log(message = it::class.java.simpleName) }
                is CommonState.Loading -> { log(message = it::class.java.simpleName) }
                is CommonState.Success -> { log(message = it::class.java.simpleName) }
                is CommonState.Error -> { log(message = it::class.java.simpleName) }
            }
        })
    }

    private fun showSearchView() {
        with(mBinding) {
            videoParentLayout.update {
                setGuidelinePercent(R.id.videoStartGuideline, 0f)
                setGuidelineBegin(R.id.videoHorizontalGuideline, dpToPx(56))
            }

            TransitionManager.beginDelayedTransition(videoParentLayout, transition)
        }
    }

    private fun hideSearchView() {
        with(mBinding) {
            videoSearchEditText.setText("")

            videoParentLayout.update {
                setGuidelinePercent(R.id.videoStartGuideline, 1f)
                setGuidelinePercent(R.id.videoHorizontalGuideline, 1f)
            }

            TransitionManager.beginDelayedTransition(videoParentLayout, transition)
        }
    }

    private fun focusOn(view: View, focus: Boolean) {
        with(view) {
            post { if(focus) requestFocus() else clearFocus() }
        }
    }

    companion object {
        val TAG = VideoFragment::class.java.simpleName

        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }
}
