package com.sary.orders_domain.model

data class OrderInfo(
  var orderStatus: String? = null,
  var orderStatusCode: Int? = null,
  var orderDate: String? = null,
  var orderTime: String? = null,
  var orderId: Int? = null
)
