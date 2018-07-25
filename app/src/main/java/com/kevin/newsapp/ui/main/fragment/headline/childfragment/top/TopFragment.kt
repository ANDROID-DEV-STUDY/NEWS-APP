package com.kevin.newsapp.ui.main.fragment.headline.childfragment.top

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.databinding.HeadlineChildFragmentTopBinding
import com.kevin.newsapp.ui.base.BaseChildFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.adapter.TopRecyclerViewAdapter
import com.kevin.newsapp.util.extensions.autoScaling
import com.kevin.newsapp.util.extensions.gone
import com.kevin.newsapp.util.extensions.onGlobalLayout
import com.kevin.newsapp.util.extensions.visible
import com.kevin.newsapp.util.state.CommonState

class TopFragment: BaseChildFragment<HeadlineChildFragmentTopBinding, TopViewModel>() {

    override val layoutResID: Int
        get() = R.layout.headline_child_fragment_top

    override val modelClass: Class<TopViewModel>
        get() = TopViewModel::class.java

    override fun onCreateView() {
        with(mBinding) {
            root.onGlobalLayout{ mBinding.topLoadingView.autoScaling(1f) }

            with(topRecyclerView) {
                adapter = TopRecyclerViewAdapter(mContext)
                layoutManager = LinearLayoutManager(mContext)
                isNestedScrollingEnabled = false
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.topHeadlines.observe(this, Observer {
            when(it) {
                is CommonState.Init -> { showOrHideLoadingView(false) }
                is CommonState.Loading -> { showOrHideLoadingView(true) }
                is CommonState.Success -> { bindData(it.data) }
                is CommonState.Error -> { /* HANDLING ERROR */ }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mViewModel.fetchTopHeadlines()
    }

    // NAMING ?
    private fun bindData(data: NewsResponse) = (mBinding.topRecyclerView.adapter as TopRecyclerViewAdapter).setData(data)

    private fun showOrHideLoadingView(show: Boolean) = with(mBinding.topLoadingView) { if(show) visible() else gone() }

    companion object {
        fun newInstance(): TopFragment = TopFragment()
    }
}
