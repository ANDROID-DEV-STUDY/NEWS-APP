package com.kevin.newsapp.util.databinding

import android.databinding.ObservableField
import io.reactivex.Observable

fun <T> asObservable(field: ObservableField<T>): Observable<T> {
    return Observable.create {emitter ->

        val initialValue: T? = field.get()

        initialValue?.let { emitter.onNext(it) }

        val callback = object: android.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: android.databinding.Observable, propertyId: Int) {
                field.get()?.let { emitter.onNext(it) }
            }
        }

        field.addOnPropertyChangedCallback(callback)

        emitter.setCancellable { field.removeOnPropertyChangedCallback(callback) }
    }
}