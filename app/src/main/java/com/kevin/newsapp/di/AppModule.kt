package com.kevin.newsapp.di

import android.app.Application
import android.content.Context
import com.kevin.newsapp.App
import com.kevin.newsapp.ui.main.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [
    AndroidSupportInjectionModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun application(app : App) : Application

    @Binds
    @Singleton
    abstract fun applicationContext(app : App) : Context

    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : MainActivity
}