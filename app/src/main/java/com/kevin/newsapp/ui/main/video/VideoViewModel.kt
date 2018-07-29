package com.kevin.newsapp.ui.main.video

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.kevin.newsapp.data.model.youtube.YoutubeVideo
import com.kevin.newsapp.data.repository.youtube.YoutubeRepository
import com.kevin.newsapp.ui.base.BaseRxViewModel
import com.kevin.newsapp.util.databinding.asObservable
import com.kevin.newsapp.util.state.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class VideoViewModel constructor(
        private val repository: YoutubeRepository
): BaseRxViewModel() {

    val videoSearchQuery: ObservableField<String> = ObservableField("")

    val videoSearchState: MutableLiveData<CommonState<List<YoutubeVideo>>> = MutableLiveData()

    fun init() {
        asObservable(videoSearchQuery)
                .filter { it.isNotBlank() }
                .debounce(500L, TimeUnit.MILLISECONDS)
                .flatMap { repository.search(it).subscribeOn(Schedulers.io()) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { videoSearchState.value = CommonState.Loading() }
                .doOnTerminate { videoSearchState.value = CommonState.Init() }
                .subscribe({
                    videoSearchState.value = CommonState.Success(it) // possible to be zero size array
                }, {
                    videoSearchState.value = CommonState.Error(it)
                })
                .addTo(mDisposable)
    }
}