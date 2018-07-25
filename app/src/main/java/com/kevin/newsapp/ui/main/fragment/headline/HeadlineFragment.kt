package com.kevin.newsapp.ui.main.fragment.headline

import android.support.v4.app.Fragment
import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentHeadlineBinding
import com.kevin.newsapp.ui.base.BaseFragment
import com.kevin.newsapp.ui.main.fragment.headline.adapter.HeadlineViewPagerAdapter
import com.kevin.newsapp.util.extensions.setUpTabPager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HeadlineFragment : BaseFragment<FragmentHeadlineBinding, HeadlineViewModel>(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    override val layoutResID: Int
        get() = R.layout.fragment_headline

    override val modelClass: Class<HeadlineViewModel>
        get() = HeadlineViewModel::class.java

    override fun onCreateView() {
        with(mBinding) {
            headlineViewPager.adapter = HeadlineViewPagerAdapter(childFragmentManager)
            setUpTabPager(headlineTabLayout, headlineViewPager)
        }
    }

    companion object {
        val TAG = HeadlineFragment::class.java.simpleName

        fun newInstance(): HeadlineFragment {
            return HeadlineFragment()
        }
    }
}
