package com.kevin.newsapp.ui.main

import android.arch.lifecycle.MutableLiveData
import com.kevin.newsapp.data.model.Response
import com.kevin.newsapp.data.repository.HeadlineRepository
import com.kevin.newsapp.data.webservice.WebServiceInfo
import com.kevin.newsapp.ui.base.BaseViewModel
import com.kevin.newsapp.util.state.WebServiceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 *
 */
class MainViewModel @Inject constructor(
        private val headlineRepository: HeadlineRepository
) : BaseViewModel() {

//    init {
//        fetchTopHeadlines()
//        fetchCategoryHeadlines("business")
//    }

    // TOP HEADLINE
    val topHeadlines : MutableLiveData<WebServiceState<Response>> = MutableLiveData()

    fun fetchTopHeadlines()
            = headlineRepository
            .topHeadlines(WebServiceInfo.country, WebServiceInfo.apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { topHeadlines.value = WebServiceState.Init() }
            .doOnSubscribe { topHeadlines.value = WebServiceState.Loading() }
            .subscribe({ response -> topHeadlines.value = WebServiceState.Success(response) },
                    { throwable -> topHeadlines.value = WebServiceState.Error(throwable) })
            .let { getCompositeDisposable().add(it) }

    // CATEGORY HEADLINE
    val categoryHeadlines : MutableLiveData<WebServiceState<Response>> = MutableLiveData()

    fun fetchCategoryHeadlines(category : String)
            = headlineRepository
            .categoryHeadlines(WebServiceInfo.country, category, WebServiceInfo.apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { categoryHeadlines.value = WebServiceState.Init() }
            .doOnSubscribe { categoryHeadlines.value = WebServiceState.Loading() }
            .subscribe({ response -> categoryHeadlines.value = WebServiceState.Success(response) },
                    { throwable -> categoryHeadlines.value = WebServiceState.Error(throwable) })
            .let { getCompositeDisposable().add(it) }

}