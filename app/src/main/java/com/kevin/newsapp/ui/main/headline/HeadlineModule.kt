package com.kevin.newsapp.ui.main.headline

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.ChildFragmentScope
import com.kevin.newsapp.di.scope.FragmentScope
import com.kevin.newsapp.ui.main.headline.children.business.BusinessFragment
import com.kevin.newsapp.ui.main.headline.children.business.BusinessModule
import com.kevin.newsapp.ui.main.headline.children.top.TopFragment
import com.kevin.newsapp.ui.main.headline.children.top.TopModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HeadlineModule {

    @Binds @FragmentScope @FragmentLevel
    abstract fun provideViewModelFactory(headlineViewModelFactory: HeadlineViewModelFactory): ViewModelProvider.Factory

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [TopModule::class])
    abstract fun contributeTopFragment(): TopFragment

    @ChildFragmentScope
    @ContributesAndroidInjector(modules = [BusinessModule::class])
    abstract fun contributeBusinessFragment(): BusinessFragment
}