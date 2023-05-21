package com.sary.core_domain.util

import com.sary.core_domain.R

sealed class Resource<T>(
  val data: T? = null,
  val message: String? = null,
  val isConnectionError: Boolean? = null
) {
  
  class Success<T>(data: T?): Resource<T>(data)
  class Error<T>(message: String, data: T? = null, isConnectionError: Boolean):
    Resource<T>(data, message, isConnectionError)
  
  class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}

fun <T> Resource.Error<T>.getError(): UiText {
  return when {
    !this.message.isNullOrEmpty() -> UiText.DynamicString(this.message)
    this.isConnectionError == false -> UiText.StringResource(R.string.general_error)
    else -> UiText.StringResource(R.string.connection_error)
  }
}
