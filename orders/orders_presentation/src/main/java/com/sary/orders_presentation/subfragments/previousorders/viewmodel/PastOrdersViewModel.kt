package com.sary.orders_presentation.subfragments.previousorders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.core_domain.R
import com.sary.core_domain.util.Resource
import com.sary.core_domain.util.UiText
import com.sary.core_domain.util.getError
import com.sary.orders_domain.model.past.PastOrderResult
import com.sary.orders_domain.use_case.PastOrdersUseCase
import com.sary.orders_presentation.subfragments.previousorders.PreviousOrdersUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PastOrdersViewModel @Inject constructor(
  private val pastOrdersUseCase: PastOrdersUseCase): ViewModel() {
  
  private val _userFlow = MutableStateFlow<PreviousOrdersUiState>(PreviousOrdersUiState.Loading)
  val userFlow: StateFlow<PreviousOrdersUiState> = _userFlow.asStateFlow()
  
  private var currentOffset: Int? = 1
  private var cachedList: MutableList<PastOrderResult> = mutableListOf()
  private var hasNextPage: Boolean = true
  private var isLoading: Boolean = false
  
  fun getPastOrders(reset: Boolean) {
    val isFirstTime = cachedList.isEmpty() && currentOffset == 1 && !isLoading
    if (reset) {
      cachedList.clear()
      hasNextPage = true
      currentOffset = 1
    }
    
    if (hasNextPage && !isLoading) viewModelScope.launch {
      pastOrdersUseCase(currentOffset ?: return@launch).collectLatest { result ->
        _userFlow.update {
          when (result) {
            is Resource.Success -> {
              isLoading = false
              result.data?.let { ordersResponse ->
                hasNextPage = ordersResponse.hasNextPage
                currentOffset = currentOffset?.plus(1)
                ordersResponse.orders.addToCache()
                PreviousOrdersUiState.Success(cachedList)
              } ?: PreviousOrdersUiState.Error(UiText.StringResource(R.string.general_error))
            }
            
            is Resource.Error -> {
              isLoading = false
              PreviousOrdersUiState.Error(result.getError())
            }
            
            is Resource.Loading -> {
              isLoading = true
              if (isFirstTime) PreviousOrdersUiState.FirstLoading else PreviousOrdersUiState.Loading
            }
          }
        }
      }
    }
  }
  
  private fun List<PastOrderResult>.addToCache() {
    cachedList.addAll(this@addToCache)
  }
}