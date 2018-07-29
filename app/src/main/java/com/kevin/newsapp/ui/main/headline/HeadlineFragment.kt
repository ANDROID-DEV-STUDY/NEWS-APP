package com.kevin.newsapp.ui.main.headline

import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.transition.ChangeBounds
import android.transition.TransitionManager
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentHeadlineBinding
import com.kevin.newsapp.ui.base.BaseFragment
import com.kevin.newsapp.ui.main.headline.adapter.HeadlineViewPagerAdapter
import com.kevin.newsapp.util.extensions.dpToPx
import com.kevin.newsapp.util.extensions.setUpTabPager
import com.kevin.newsapp.util.extensions.update
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HeadlineFragment:
        BaseFragment<FragmentHeadlineBinding, HeadlineViewModel>(),
        HasSupportFragmentInjector,
        HeadlineScrollEventListener
{

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override val layoutResID: Int
        get() = R.layout.fragment_headline

    override val modelClass: Class<HeadlineViewModel>
        get() = HeadlineViewModel::class.java

    override fun onCreateView() {
        with(mBinding) {
            with(headlineViewPager) {
                adapter = HeadlineViewPagerAdapter(childFragmentManager)
                setUpTabPager(headlineTabLayout, this)
            }
        }
    }

    override fun onScale(expand: Boolean) {
        val oldMargin = (mBinding.headlineGuideline.layoutParams as ConstraintLayout.LayoutParams).guideBegin
        val newMargin = if(expand) dpToPx(64) else 0

        if(newMargin != oldMargin) {
            with(mBinding.headlineParentLayout) {
                update { setGuidelineBegin(R.id.headlineGuideline, newMargin) }
                TransitionManager.beginDelayedTransition(this@with, ChangeBounds().apply { duration = 500 })
            }
        }
    }

    companion object {
        val TAG = HeadlineFragment::class.java.simpleName

        fun newInstance(): HeadlineFragment {
            return HeadlineFragment()
        }
    }
}
