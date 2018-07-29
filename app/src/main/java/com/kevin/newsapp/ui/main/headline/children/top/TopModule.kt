package com.kevin.newsapp.ui.main.headline.children.top

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.ChildFragmentLevel
import com.kevin.newsapp.di.scope.ChildFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class TopModule {

    @Binds @ChildFragmentScope @ChildFragmentLevel
    abstract fun provideViewModelFactory(topViewModelFactory: TopViewModelFactory): ViewModelProvider.Factory
}