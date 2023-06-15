package com.task.tmdb.common

sealed class Result<out T> {
    object Empty : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<out T>(val result: T) : Result<T>()
    data class Error(val message: String?, val t: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Empty -> "{ Result is EMPTY }"
            is Loading -> "{ Result is LOADING }"
            is Success -> this.result.toString()
            is Error -> """
                {
                    Result is ERROR, data:
                    message -> $message
                }
            """.trimIndent()
        }
    }
}