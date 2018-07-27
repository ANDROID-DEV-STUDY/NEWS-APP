package com.kevin.newsapp.ui.main.fragment.headline.childfragment.top

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kevin.newsapp.R
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.databinding.HeadlineChildFragmentTopBinding
import com.kevin.newsapp.ui.article.ArticleActivity
import com.kevin.newsapp.ui.base.BaseChildFragment
import com.kevin.newsapp.ui.main.fragment.headline.HeadlineTouchEventListener
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.adapter.TopRecyclerViewAdapter
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.adapter.TopRecyclerViewItemClickListener
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
                adapter = TopRecyclerViewAdapter(mContext, object: TopRecyclerViewItemClickListener {
                    override fun onClick(url: String) {
                        startActivity(
                                Intent(mContext, ArticleActivity::class.java)
                                        .putExtra(ArticleActivity.ARTICLE_URL, url)
                        )
                    }
                })
                layoutManager = LinearLayoutManager(mContext)
                isNestedScrollingEnabled = false
            }

            topNestedScrollView.headlineTouchEventListener = parentFragment as? HeadlineTouchEventListener
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO onStart() 다시 호출되면 뷰가 꼬인다.
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

    private fun bindData(data: NewsResponse)
            = (mBinding.topRecyclerView.adapter as TopRecyclerViewAdapter).setData(data)

    private fun showOrHideLoadingView(show: Boolean)
            = with(mBinding.topLoadingView) { if(show) visible() else gone() }

    companion object {
        fun newInstance(): TopFragment = TopFragment()
    }
}
