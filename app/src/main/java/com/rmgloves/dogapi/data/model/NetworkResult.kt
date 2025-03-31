package com.rmgloves.dogapi.data.model

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val error: ErrorMessage) : NetworkResult<Nothing>()
}