package com.avtograv.common.model

import android.util.Log

private const val TAG = "Exception"

internal sealed class Result<out T>

internal data class Success<T>(val data: T) : Result<T>()

internal data class Failure(val exception: Throwable) : Result<Nothing>()

internal inline fun <R> runCatchingResult(block: () -> R): Result<R> {
    return try {
        Success(block())
    } catch (e: Throwable) {
        Log.d(TAG, "Exception: $e")
        Failure(e)
    }
}