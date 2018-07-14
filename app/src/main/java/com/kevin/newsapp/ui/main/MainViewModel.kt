package com.kevin.newsapp.ui.main

import android.arch.lifecycle.MutableLiveData
import com.kevin.newsapp.BuildConfig
import com.kevin.newsapp.data.model.Response
import com.kevin.newsapp.data.repository.HeadlineRepository
import com.kevin.newsapp.ui.base.BaseViewModel
import com.kevin.newsapp.util.state.NetworkState
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
    val topHeadlines : MutableLiveData<NetworkState<Response>> = MutableLiveData()

    fun fetchTopHeadlines()
            = headlineRepository
            .topHeadlines(BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { topHeadlines.value = NetworkState.Init() }
            .doOnSubscribe { topHeadlines.value = NetworkState.Loading() }
            .subscribe({ response -> topHeadlines.value = NetworkState.Success(response) },
                    { throwable -> topHeadlines.value = NetworkState.Error(throwable) })
            .also { getCompositeDisposable().add(it) }

    // CATEGORY HEADLINE
    val categoryHeadlines : MutableLiveData<NetworkState<Response>> = MutableLiveData()

    fun fetchCategoryHeadlines(category : String)
            = headlineRepository.categoryHeadlines(category, BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { categoryHeadlines.value = NetworkState.Init() }
            .doOnSubscribe { categoryHeadlines.value = NetworkState.Loading() }
            .subscribe({ response -> categoryHeadlines.value = NetworkState.Success(response) },
                    { throwable -> categoryHeadlines.value = NetworkState.Error(throwable) })
            .also { getCompositeDisposable().add(it) }

}