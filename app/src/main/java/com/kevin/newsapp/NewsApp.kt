package com.kevin.newsapp

import com.kevin.newsapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NewsApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
        = DaggerAppComponent.builder().create(this)

    /* in AndroidInjector.Builder
    @Override
    public final AndroidInjector<T> create(T instance) {
        seedInstance(instance);
        return build();
    }
    */
}