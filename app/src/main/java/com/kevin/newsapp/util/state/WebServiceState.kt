package com.kevin.newsapp.util.state

sealed class WebServiceState<out T> {
    class Init : WebServiceState<Nothing>()
    class Loading : WebServiceState<Nothing>()
    class Success<out T>(val data: T) : WebServiceState<T>()
    class Error(val throwable: Throwable) : WebServiceState<Nothing>()
}