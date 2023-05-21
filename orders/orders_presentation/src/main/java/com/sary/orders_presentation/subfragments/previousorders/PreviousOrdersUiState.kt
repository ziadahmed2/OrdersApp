package com.sary.orders_presentation.subfragments.previousorders

import com.sary.core_domain.util.UiText
import com.sary.orders_domain.model.past.PastOrderResult

sealed interface PreviousOrdersUiState {
  object Loading: PreviousOrdersUiState
  data class Error(val uiText: UiText): PreviousOrdersUiState
  data class Success(val previousOrderResults: List<PastOrderResult>): PreviousOrdersUiState
}
