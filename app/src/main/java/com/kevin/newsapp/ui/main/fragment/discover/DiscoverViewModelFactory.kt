package com.kevin.newsapp.ui.main.fragment.discover

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class DiscoverViewModelFactory @Inject constructor(

): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiscoverViewModel() as T
    }
}