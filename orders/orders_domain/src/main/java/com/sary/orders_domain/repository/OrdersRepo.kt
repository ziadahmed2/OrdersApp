package com.sary.orders_domain.repository

import com.sary.core_domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface OrdersRepo {
  
  suspend fun getCurrentOrders(page: Int): Flow<Resource<List<Unit>>>
}