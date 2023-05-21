package com.sary.orders_domain.model.current

data class CurrentOrders(
  var hasNextPage: Boolean,
  var orders: List<CurrentOrderResult> = arrayListOf()
)
