package com.sary.orders_data.remote

import com.sary.orders_data.remote.dto.OrdersResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SaryApi {
  companion object {
    
    private const val ORDERS_URL = "baskets/cart"
    private const val CURRENT_ORDERS_STATUS = "current"
    private const val PAST_ORDERS_STATUS = "past-orders"
  }
  
  @GET(ORDERS_URL)
  suspend fun getCurrentOrders(
    @Query("page_size")
    pageSize: Int = 10,
    @Query("page")
    page: Int,
    @Query("status")
    status: String = CURRENT_ORDERS_STATUS
  ): OrdersResponseDto
  
  @GET(ORDERS_URL)
  suspend fun getPastOrders(
    @Query("page_size")
    pageSize: Int = 10,
    @Query("page")
    page: Int,
    @Query("status")
    status: String = PAST_ORDERS_STATUS
  ): OrdersResponseDto
}