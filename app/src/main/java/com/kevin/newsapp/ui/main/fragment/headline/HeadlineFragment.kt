package com.kevin.newsapp.ui.main.fragment.headline

import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentHeadlineBinding
import com.kevin.newsapp.ui.base.BaseFragment
import com.kevin.newsapp.ui.main.fragment.headline.adapter.HeadlineViewPagerAdapter
import com.kevin.newsapp.util.extensions.setUpTabPager

class HeadlineFragment : BaseFragment<FragmentHeadlineBinding, HeadlineViewModel>() {

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
