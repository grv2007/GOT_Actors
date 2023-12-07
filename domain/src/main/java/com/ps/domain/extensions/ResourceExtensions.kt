package com.ps.domain.extensions


import com.ps.domain.utils.Resource
import java.io.IOException

inline fun <T> Resource<T>.onSuccess(action: (value: T) -> Unit): Resource<T> {
    if (this is Resource.Success) action(this.data)
    return this
}

inline fun <T> Resource<T>.onFailure(action: (error: IOException) -> Unit): Resource<T> {
    if (this is Resource.Failure) action(error)
    return this
}
