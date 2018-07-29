package com.kevin.newsapp.ui.main.headline.children.business

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.ChildFragmentLevel
import com.kevin.newsapp.di.scope.ChildFragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class BusinessModule {

    @Binds @ChildFragmentScope @ChildFragmentLevel
    abstract fun provideViewModelFactory(businessViewModelFactory: BusinessViewModelFactory): ViewModelProvider.Factory
}