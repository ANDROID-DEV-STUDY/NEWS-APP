package com.kevin.newsapp.ui.main.headline.children.business

import android.arch.lifecycle.MutableLiveData
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.data.repository.news.NewsRepository
import com.kevin.newsapp.ui.base.BaseRxViewModel
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class BusinessViewModel constructor(
        private val newsRepository: NewsRepository
): BaseRxViewModel() {

    // TOP HEADLINE
    val businessHeadlines : MutableLiveData<CommonState<NewsResponse>> = MutableLiveData()

    fun fetchBusinessHeadlines(){
        newsRepository.fetchCategoryHeadlines("business")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { businessHeadlines.value = CommonState.Loading() }
                .doOnTerminate { businessHeadlines.value = CommonState.Init() }
                .subscribe({ response -> businessHeadlines.value = CommonState.Success(response) },
                        { throwable -> businessHeadlines.value = CommonState.Error(throwable) })
                .addTo(mDisposable)
    }

}