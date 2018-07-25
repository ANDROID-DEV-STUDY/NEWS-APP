package com.kevin.newsapp.ui.main.fragment.discover

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class DiscoverModule {

    @Binds @FragmentScope @FragmentLevel
    abstract fun provideViewModelFactory(discoverViewModelFactory: DiscoverViewModelFactory): ViewModelProvider.Factory
}