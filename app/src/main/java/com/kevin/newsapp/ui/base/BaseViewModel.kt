package com.kevin.newsapp.ui.base

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    open fun getCompositeDisposable() : CompositeDisposable = compositeDisposable

    override fun onCleared() {
        compositeDisposable.takeIf { it.isDisposed }?.clear()
        super.onCleared()
    }
}