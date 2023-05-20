package com.sary.orders_domain.model

data class CurrentOrderResult(
  var orderInfo: OrderInfo? = null,
  var shipments: List<Shipment>? = arrayListOf()
)