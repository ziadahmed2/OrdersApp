package com.sary.core_domain.util

sealed class UiEvent {
  object Success: UiEvent()
  object NavigateUp: UiEvent()
  data class ShowSnackbar(val message: UiText): UiEvent()
}