package com.sary.orders_presentation.currentorders

import com.sary.core_domain.util.UiText

sealed interface UiState {
  object Loading: UiState
  data class Error(val uiText: UiText): UiState
  data class Success(val currentOrdersResult: List<Any>): UiState
}
