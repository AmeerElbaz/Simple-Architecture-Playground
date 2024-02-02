package com.elbaz.sample.data.models


sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<out T>(val msg: String) : Result<String>()
}