package com.kevin.newsapp.util.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.kevin.newsapp.util.livedata.NonNullMediatorLiveData

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()

    mediator.addSource(this, { it?.let { mediator.value = it } })

    return mediator
}

fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, react: (data: T) -> Unit) {
    observe(owner, Observer { it?.let(react) })
}