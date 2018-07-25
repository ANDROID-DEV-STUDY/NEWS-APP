package com.kevin.newsapp.ui.main.fragment.video

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.jakewharton.rxbinding2.view.focusChanges
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentVideoBinding
import com.kevin.newsapp.ui.base.BaseFragment
import com.kevin.newsapp.ui.main.fragment.video.adapter.VideoViewPagerAdapter
import com.kevin.newsapp.util.extensions.*
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo

class VideoFragment: BaseFragment<FragmentVideoBinding, VideoViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_video

    override val modelClass: Class<VideoViewModel>
        get() = VideoViewModel::class.java

    override fun onCreateView() {
        with(mBinding) {
            videoSearchButton.onClick { showOrHideSearchView(true) }

            videoSearchCancelButton.onClick { showOrHideSearchView(false) }

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
                is CommonState.Init -> { Log.i(TAG, it::class.java.simpleName) }
                is CommonState.Loading -> { Log.i(TAG, it::class.java.simpleName) }
                is CommonState.Success -> { Log.i(TAG, it::class.java.simpleName) }
                is CommonState.Error -> { Log.i(TAG, it::class.java.simpleName) }
            }
        })
    }

    private fun showOrHideSearchView(show: Boolean) {
        with(mBinding) {
            videoParentLayout.update {
                setGuidelinePercent(R.id.videoStartGuideline, if(show) 0f else 1f)

                if(show)
                    setGuidelineBegin(R.id.videoHorizontalGuideline, dpToPx(56))
                else
                    setGuidelinePercent(R.id.videoHorizontalGuideline, 1f)

                TransitionManager.beginDelayedTransition(videoParentLayout, ChangeBounds().apply {
                    duration = 500L
                    interpolator = AccelerateDecelerateInterpolator()
                })
            }

            focusOn(videoSearchEditText, show)
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
