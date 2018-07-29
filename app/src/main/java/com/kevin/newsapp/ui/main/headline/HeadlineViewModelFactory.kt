package com.kevin.newsapp.ui.main.headline

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class HeadlineViewModelFactory @Inject constructor(

): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeadlineViewModel() as T
    }
}