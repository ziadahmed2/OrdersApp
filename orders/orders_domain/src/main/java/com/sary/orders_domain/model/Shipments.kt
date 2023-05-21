package com.sary.orders_domain.model

data class Shipment(
  var shipmentCode: String? = null,
  var deliveryDate: String? = null,
  var intervalStartTime12: String? = null,
  var intervalEndTime12: String? = null,
  var deliveryCurrentStatus: String? = null,
  var items: List<Items> = arrayListOf()
)
