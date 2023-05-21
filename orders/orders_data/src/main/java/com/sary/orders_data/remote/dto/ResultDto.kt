package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResultDto(
  
  @SerializedName("cart_id") var cartId: Int? = null,
  @SerializedName("cart_total") var cartTotal: Double? = null,
  @SerializedName("order_status") var orderStatus: String? = null,
  @SerializedName("order_status_code") var orderStatusCode: Int? = null,
  @SerializedName("order_date") var orderDate: String? = null,
  @SerializedName("order_time") var orderTime: String? = null,
  @SerializedName("delivered_at_date") var deliveredAtDate: String? = null,
  @SerializedName("order_id") var orderId: Int? = null,
  @SerializedName("order_delivery_rating") var orderDeliveryRating: ArrayList<OrderDeliveryRatingDto> = arrayListOf(),
  @SerializedName("items") var items: ArrayList<ItemsDto>? = arrayListOf(),
  @SerializedName("items_count") var itemsCount: String? = null,
  @SerializedName("shipments") var shipments: ArrayList<ShipmentsDto>? = arrayListOf(),
  @SerializedName("order_rating_status") var orderRatingStatus: Int? = null
)