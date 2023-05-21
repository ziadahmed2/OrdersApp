package com.sary.orders_domain.model.current

import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.model.Shipment

data class CurrentOrderResult(
  var orderInfo: OrderInfo? = null,
  var shipments: List<Shipment>? = arrayListOf()
)