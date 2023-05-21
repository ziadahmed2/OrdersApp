package com.sary.orders_domain.repository

import com.sary.core_domain.util.Resource
import com.sary.orders_domain.model.current.CurrentOrders
import com.sary.orders_domain.model.past.PastOrders
import kotlinx.coroutines.flow.Flow

interface OrdersRepo {
  
  suspend fun getCurrentOrders(page: Int): Flow<Resource<CurrentOrders>>
  suspend fun getPastOrders(page: Int): Flow<Resource<PastOrders>>
}