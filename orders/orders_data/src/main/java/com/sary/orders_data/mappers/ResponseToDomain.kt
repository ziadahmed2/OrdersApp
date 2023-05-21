package com.sary.orders_data.mappers

import com.sary.orders_data.remote.dto.ItemsDto
import com.sary.orders_data.remote.dto.OrdersResponseDto
import com.sary.orders_data.remote.dto.ResultDto
import com.sary.orders_data.remote.dto.ShipmentsDto
import com.sary.orders_domain.model.current.CurrentOrderResult
import com.sary.orders_domain.model.current.CurrentOrders
import com.sary.orders_domain.model.Items
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.model.Shipment
import com.sary.orders_domain.model.past.PastOrderResult
import com.sary.orders_domain.model.past.PastOrders

fun OrdersResponseDto.toPastOrdersDomain(): PastOrders {
  return PastOrders(
    hasNextPage = next.isNullOrBlank().not(),
    orders = result.map { it.toPastOrdersDomain() }
  )
}

fun ResultDto.toPastOrdersDomain(): PastOrderResult {
  return PastOrderResult(
    orderInfo = OrderInfo(
      orderStatus = orderStatus,
      orderStatusCode = orderStatusCode,
      orderDate = orderDate,
      orderTime = orderTime,
      orderId = orderId
    ),
    cartTotal = cartTotal,
    canRate = orderDeliveryRating.isEmpty(),
    items = items?.map { it.toDomain() }
  )
}

fun OrdersResponseDto.toCurrentOrdersDomain(): CurrentOrders {
  return CurrentOrders(
    hasNextPage = next.isNullOrBlank().not(),
    orders = result.map { it.toCurrentOrdersDomain() }
  )
}

fun ResultDto.toCurrentOrdersDomain(): CurrentOrderResult {
  return CurrentOrderResult(
    orderInfo = OrderInfo(
      orderStatus = orderStatus,
      orderStatusCode = orderStatusCode,
      orderDate = orderDate,
      orderTime = orderTime,
      orderId = orderId
    ),
    shipments = shipments?.map { it.toDomain() },
  )
}

fun ShipmentsDto.toDomain(): Shipment {
  return Shipment(
    shipmentCode = shipmentCode,
    deliveryDate = deliveryDate,
    intervalStartTime12 = intervalStartTime12,
    intervalEndTime12 = intervalEndTime12,
    deliveryCurrentStatus = deliveryCurrentStatus,
    items = items.map { it.toDomain() }
  )
}

fun ItemsDto.toDomain(): Items {
  return Items(image = image)
}