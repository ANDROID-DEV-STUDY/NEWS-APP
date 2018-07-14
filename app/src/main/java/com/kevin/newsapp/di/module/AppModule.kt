package com.kevin.newsapp.di.module

import android.content.Context
import com.kevin.newsapp.NewsApp
import com.kevin.newsapp.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds @Singleton
    abstract fun applicationContext(newsApp : NewsApp) : Context

    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : MainActivity
}