package com.kevin.newsapp.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class BaseRxViewModel: BaseViewModel() {

    val mDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mDisposable.clear()
    }
}