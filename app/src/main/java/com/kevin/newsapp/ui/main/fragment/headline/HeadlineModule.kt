package com.kevin.newsapp.ui.main.fragment.headline

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.ChildFragmentScope
import com.kevin.newsapp.di.scope.FragmentScope
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.TopFragment
import com.kevin.newsapp.ui.main.fragment.headline.childfragment.top.TopModule
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
}