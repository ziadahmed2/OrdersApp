package com.sary.orders_presentation.currentorders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.core_domain.util.Resource
import com.sary.orders_domain.use_case.CurrentOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentOrdersViewModel @Inject constructor(
  private val currentOrdersUseCase: CurrentOrdersUseCase
): ViewModel() {
  
  fun getCurrentOrders(page: Int) {
    viewModelScope.launch {
      currentOrdersUseCase(page)
        .collectLatest { result ->
          when (result) {
            is Resource.Success -> {
              result.data?.let { orders ->
              
              }
            }
            is Resource.Error -> {
            }
            is Resource.Loading -> {
            }
          }
        }
    }
  }
}