package com.sary.orders_domain.model

data class CurrentOrders(
  var hasNextPage: Boolean,
  var orders: List<CurrentOrderResult> = arrayListOf()
)
