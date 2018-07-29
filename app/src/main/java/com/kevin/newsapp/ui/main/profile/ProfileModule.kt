package com.kevin.newsapp.ui.main.profile

import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.di.qualifier.FragmentLevel
import com.kevin.newsapp.di.scope.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class ProfileModule {

    @Binds @FragmentScope @FragmentLevel
    abstract fun provideViewModelFactory(profileViewModelFactory: ProfileViewModelFactory): ViewModelProvider.Factory
}