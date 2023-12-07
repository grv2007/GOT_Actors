package com.ps.data.extensions


import com.ps.domain.utils.Resource
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

suspend fun <T : Any> handleAPICall(
    apiCall: suspend () -> Response<T>
): Resource<T> {
    return try {
        apiCall.invoke().handleAPIResponse()
    } catch (e: Exception) {
        e.printStackTrace()
        return when (e) {
            is UnknownHostException -> Resource.Failure(IOException())
            else -> Resource.Failure(IOException())
        }
    }
}

private fun <T : Any> Response<T>.handleAPIResponse(): Resource<T> {
    val responseBody = body() as T
    if (isSuccess()) {
        return Resource.Success(responseBody)
    }
    return Resource.Failure(
        IOException()
    )
}

private fun <T : Any> Response<T>.isSuccess(): Boolean = isSuccessful