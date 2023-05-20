package com.sary.orders_data.mappers

import com.sary.orders_data.remote.dto.ItemsDto
import com.sary.orders_data.remote.dto.OrdersResponseDto
import com.sary.orders_data.remote.dto.ResultDto
import com.sary.orders_data.remote.dto.ShipmentsDto
import com.sary.orders_domain.model.CurrentOrderResult
import com.sary.orders_domain.model.CurrentOrders
import com.sary.orders_domain.model.Items
import com.sary.orders_domain.model.OrderInfo
import com.sary.orders_domain.model.Shipment

fun OrdersResponseDto.toDomain(): CurrentOrders {
  return CurrentOrders(
    hasNextPage = next.isNullOrBlank().not(),
    orders = result.map { it.toDomain() }
  )
}

fun ResultDto.toDomain(): CurrentOrderResult {
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