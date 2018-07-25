package com.kevin.newsapp.di.module

import com.kevin.newsapp.di.scope.ActivityScope
import com.kevin.newsapp.ui.main.activity.MainActivity
import com.kevin.newsapp.ui.main.activity.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributer {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributesMainActivity() : MainActivity
}