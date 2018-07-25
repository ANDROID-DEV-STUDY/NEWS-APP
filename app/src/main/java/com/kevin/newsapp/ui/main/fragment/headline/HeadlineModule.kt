package com.kevin.newsapp.ui.main.fragment.headline

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class HeadlineModule {

    @Binds @FragmentScope @FragmentLevel
    abstract fun provideViewModelFactory(headlineViewModelFactory: HeadlineViewModelFactory): ViewModelProvider.Factory
}