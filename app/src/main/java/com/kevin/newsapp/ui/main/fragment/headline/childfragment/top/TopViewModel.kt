package com.kevin.newsapp.ui.main.fragment.headline.childfragment.top

import android.arch.lifecycle.MutableLiveData
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.data.repository.news.NewsRepository
import com.kevin.newsapp.ui.base.BaseRxViewModel
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class TopViewModel constructor(
        private val newsRepository: NewsRepository
): BaseRxViewModel() {

    // TOP HEADLINE
    val topHeadlines : MutableLiveData<CommonState<NewsResponse>> = MutableLiveData()

    fun fetchTopHeadlines()
            = newsRepository.topHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { topHeadlines.value = CommonState.Loading() }
            .doOnTerminate { topHeadlines.value = CommonState.Init() }
            .subscribe({ response -> topHeadlines.value = CommonState.Success(response) },
                    { throwable -> topHeadlines.value = CommonState.Error(throwable) })
            .addTo(mDisposable)
}