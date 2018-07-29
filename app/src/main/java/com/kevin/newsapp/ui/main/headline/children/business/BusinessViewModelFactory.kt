package com.kevin.newsapp.ui.main.headline.children.business

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kevin.newsapp.data.repository.news.NewsRepository
import javax.inject.Inject

class BusinessViewModelFactory @Inject constructor(
        private val repository: NewsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BusinessViewModel(repository) as T
    }
}