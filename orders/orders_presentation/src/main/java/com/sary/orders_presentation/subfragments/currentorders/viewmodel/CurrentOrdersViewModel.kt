package com.sary.orders_presentation.subfragments.currentorders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sary.core_domain.R
import com.sary.core_domain.util.Resource
import com.sary.core_domain.util.UiText
import com.sary.core_domain.util.getError
import com.sary.orders_domain.model.current.CurrentOrderResult
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.use_case.CurrentOrdersUseCase
import com.sary.orders_presentation.subfragments.currentorders.CurrentOrdersUiState
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
  
  private val _userFlow = MutableStateFlow<CurrentOrdersUiState>(CurrentOrdersUiState.Loading)
  val userFlow: StateFlow<CurrentOrdersUiState> = _userFlow.asStateFlow()
  
  private var currentOffset: Int? = 1
  private var cachedList: MutableList<CurrentOrderResult> = mutableListOf()
  private var hasNextPage: Boolean = true
  private var isLoading: Boolean = false
  
  fun getCurrentOrders(reset: Boolean) {
    val isFirstTime = cachedList.isEmpty() && currentOffset == 1 && !isLoading
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
                CurrentOrdersUiState.Success(cachedList.mapToCurrentOrdersAdapter())
              } ?: CurrentOrdersUiState.Error(UiText.StringResource(R.string.general_error))
            }
            
            is Resource.Error -> {
              isLoading = false
              CurrentOrdersUiState.Error(result.getError())
            }
            
            is Resource.Loading -> {
              isLoading = true
              if (isFirstTime) CurrentOrdersUiState.FirstLoading else CurrentOrdersUiState.Loading
            }
          }
        }
      }
    }
  }
  
  private fun List<CurrentOrderResult>.addToCache() {
    cachedList.addAll(this@addToCache)
  }
  
  private fun List<CurrentOrderResult>.mapToCurrentOrdersAdapter(): List<Any> {
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