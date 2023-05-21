package com.sary.orders_domain.repository

import com.sary.core_domain.util.Resource
import com.sary.orders_domain.model.CurrentOrders
import kotlinx.coroutines.flow.Flow

interface OrdersRepo {
  
  suspend fun getCurrentOrders(page: Int): Flow<Resource<CurrentOrders>>
}