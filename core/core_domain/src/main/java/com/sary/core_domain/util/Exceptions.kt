package com.sary.core_domain.util

sealed class Exceptions(msg: String): Throwable(msg) {
  class NetworkNotAvailableException: Exceptions("Network is not available.")
  class DataRetrievingFailException(errorMsg: String): Exceptions(errorMsg)
  class NoDataException: Exceptions("No data.")
  class ServerError: Exceptions("Server Error.")
  class GeneralError: Exceptions("General Error.")
}