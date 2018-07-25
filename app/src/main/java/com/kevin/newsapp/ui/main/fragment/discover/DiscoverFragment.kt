package com.kevin.newsapp.ui.main.fragment.discover

import com.kevin.newsapp.R
import com.kevin.newsapp.databinding.FragmentDiscoverBinding
import com.kevin.newsapp.ui.base.BaseFragment

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding, DiscoverViewModel>() {

    override val layoutResID: Int
        get() = R.layout.fragment_discover

    override val modelClass: Class<DiscoverViewModel>
        get() = DiscoverViewModel::class.java

    override fun onCreateView() {

    }

    companion object {
        val TAG = DiscoverFragment::class.java.simpleName

        fun newInstance(): DiscoverFragment {
            return DiscoverFragment()
        }
    }
}
