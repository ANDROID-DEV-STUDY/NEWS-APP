package com.kevin.newsapp.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
        private val creators : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    // TODO Study
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass as Class<ViewModel>]
                ?: creators.entries.firstOrNull{(c, _) -> modelClass.isAssignableFrom(c)}?.value
                ?: throw IllegalArgumentException("")

        return creator.get() as T
    }
}