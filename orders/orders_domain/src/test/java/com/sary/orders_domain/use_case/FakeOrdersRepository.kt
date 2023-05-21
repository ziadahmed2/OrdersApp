package com.sary.orders_domain.use_case

import com.sary.core_domain.util.Resource
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.model.Shipment
import com.sary.orders_domain.model.current.CurrentOrderResult
import com.sary.orders_domain.model.current.CurrentOrders
import com.sary.orders_domain.model.past.PastOrderResult
import com.sary.orders_domain.model.past.PastOrders
import com.sary.orders_domain.repository.OrdersRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeOrdersRepository: OrdersRepo {
  
  private val currentOrders = CurrentOrderResult(
    orderInfo = OrderInfo(),
    shipments = listOf(
      Shipment(shipmentCode = "123")
    )
  )
  private val currentOrdersToInsert = CurrentOrders(true, listOf(currentOrders))
  
  private val pastOrders = PastOrderResult(
    orderInfo = OrderInfo(),
    cartTotal = "500"
  )
  private val pastOrdersToInsert = PastOrders(true, listOf(pastOrders))
  
  override suspend fun getCurrentOrders(page: Int): Flow<Resource<CurrentOrders>> {
    return flow {
      emit(Resource.Success<CurrentOrders>(currentOrdersToInsert))
    }
  }
  
  override suspend fun getPastOrders(page: Int): Flow<Resource<PastOrders>> {
    return flow {
      emit(Resource.Success<PastOrders>(pastOrdersToInsert))
    }
  }
}