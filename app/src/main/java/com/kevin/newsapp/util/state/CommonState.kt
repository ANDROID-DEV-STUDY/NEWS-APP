package com.kevin.newsapp.util.state

sealed class CommonState<out T> {
    class Init : CommonState<Nothing>()
    class Loading : CommonState<Nothing>()
    class Success<out T>(val data: T) : CommonState<T>()
    class Error(val throwable: Throwable) : CommonState<Nothing>()
}