package com.kevin.newsapp.di.component

import com.kevin.newsapp.NewsApp
import com.kevin.newsapp.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>()
}