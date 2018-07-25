package com.kevin.newsapp.di.component

import com.kevin.newsapp.NewsApp
import com.kevin.newsapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityContributer::class,
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
interface AppComponent : AndroidInjector<NewsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsApp>()
}