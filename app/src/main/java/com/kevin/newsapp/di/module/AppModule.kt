package com.kevin.newsapp.di.module

import android.app.Application
import android.content.Context
import com.kevin.newsapp.NewsApp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds @Singleton
    abstract fun provideApplication(newsApp: NewsApp): Application

    @Binds @Singleton
    abstract fun provideContext(newsApp : NewsApp): Context
}