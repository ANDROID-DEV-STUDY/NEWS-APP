package com.kevin.newsapp.ui.main.fragment.headline

import android.arch.lifecycle.MutableLiveData
import com.kevin.newsapp.data.model.news.NewsResponse
import com.kevin.newsapp.data.repository.news.NewsRepository
import com.kevin.newsapp.ui.base.BaseRxViewModel
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class HeadlineViewModel constructor(
        private val newsRepository: NewsRepository
) : BaseRxViewModel() {

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

    // CATEGORY HEADLINE
    val categoryHeadlines : MutableLiveData<CommonState<NewsResponse>> = MutableLiveData()

    fun fetchCategoryHeadlines(category : String)
            = newsRepository.categoryHeadlines(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { categoryHeadlines.value = CommonState.Loading() }
            .doOnTerminate { categoryHeadlines.value = CommonState.Init() }
            .subscribe({ response -> categoryHeadlines.value = CommonState.Success(response) },
                    { throwable -> categoryHeadlines.value = CommonState.Error(throwable) })
            .addTo(mDisposable)

}