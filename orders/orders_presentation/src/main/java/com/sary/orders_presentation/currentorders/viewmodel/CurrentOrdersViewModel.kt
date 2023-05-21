package com.sary.orders_presentation.currentorders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.core_domain.R
import com.sary.core_domain.util.Resource
import com.sary.core_domain.util.UiText
import com.sary.core_domain.util.getError
import com.sary.orders_domain.model.CurrentOrderResult
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.use_case.CurrentOrdersUseCase
import com.sary.orders_presentation.currentorders.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentOrdersViewModel @Inject constructor(
  private val currentOrdersUseCase: CurrentOrdersUseCase): ViewModel() {
  
  private val _userFlow = MutableStateFlow<UiState>(UiState.Loading)
  val userFlow: StateFlow<UiState> = _userFlow.asStateFlow()
  
  private var currentOffset: Int? = 1
  private var cachedList: MutableList<CurrentOrderResult> = mutableListOf()
  private var hasNextPage: Boolean = true
  private var isLoading: Boolean = false
  
  fun getCurrentOrders(reset: Boolean) {
    if (reset) {
      cachedList.clear()
      hasNextPage = true
      currentOffset = 1
    }
    
    if (hasNextPage && !isLoading) viewModelScope.launch {
      currentOrdersUseCase(currentOffset ?: return@launch).collectLatest { result ->
        _userFlow.update {
          when (result) {
            is Resource.Success -> {
              isLoading = false
              result.data?.let { ordersResponse ->
                hasNextPage = ordersResponse.hasNextPage
                currentOffset = currentOffset?.plus(1)
                ordersResponse.orders.addToCache()
                UiState.Success(cachedList.mapToTransactionsToAdapter())
              } ?: UiState.Error(UiText.StringResource(R.string.general_error))
            }
            
            is Resource.Error -> {
              isLoading = false
              UiState.Error(result.getError())
            }
            
            is Resource.Loading -> {
              isLoading = true
              UiState.Loading
            }
          }
        }
      }
    }
  }
  
  private fun List<CurrentOrderResult>.addToCache() {
    cachedList.addAll(this@addToCache)
  }
  
  private fun List<CurrentOrderResult>.mapToTransactionsToAdapter(): List<Any> {
    val map = mutableMapOf<OrderInfo, MutableList<Any>>()
    
    forEach { item ->
      val orderInfo = item.orderInfo
      val shipments = item.shipments
      
      orderInfo?.let {
        map[it] = mutableListOf<Any>().apply {
          add(it)
          addAll(shipments ?: emptyList())
          add("Divider")
        }
      }
    }
    
    return map.values.flatten()
  }
}