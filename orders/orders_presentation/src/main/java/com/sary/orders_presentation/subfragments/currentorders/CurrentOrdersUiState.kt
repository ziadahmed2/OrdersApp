package com.sary.orders_presentation.subfragments.currentorders

import com.sary.core_domain.util.UiText

sealed interface CurrentOrdersUiState {
  object Loading: CurrentOrdersUiState
  data class Error(val uiText: UiText): CurrentOrdersUiState
  data class Success(val currentOrdersResult: List<Any>): CurrentOrdersUiState
}
