package com.sary.orders_domain.model.past

data class PastOrders(
  var hasNextPage: Boolean,
  var orders: List<PastOrderResult> = arrayListOf()
)
