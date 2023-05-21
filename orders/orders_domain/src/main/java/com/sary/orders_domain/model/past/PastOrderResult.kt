package com.sary.orders_domain.model.past

import com.sary.orders_domain.model.Items
import com.sary.orders_domain.model.OrderInfo

data class PastOrderResult(
  var orderInfo: OrderInfo? = null,
  var cartTotal: String? = null,
  var canRate: Boolean? = false,
  var items: List<Items>? = arrayListOf()
)