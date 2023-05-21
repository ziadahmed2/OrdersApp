package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class OrderDeliveryRatingDto(
  
  @SerializedName("delivery_date") var deliveryDate: String? = null,
  @SerializedName("delivery_time") var deliveryTime: String? = null,
  @SerializedName("driver") var driver: Int? = null,
  @SerializedName("driver_name") var driverName: String? = null,
  @SerializedName("delivery_total") var deliveryTotal: Double? = null,
  @SerializedName("delivered_today") var deliveredToday: Boolean? = null,
  @SerializedName("delivery_items") var deliveryItems: DeliveryItemsDto? = null,
  @SerializedName("delivery_shipments_ids") var deliveryShipmentsIds: ArrayList<Int> = arrayListOf(),
  @SerializedName("delivery_rating_status") var deliveryRatingStatus: Int? = null,
  @SerializedName("order_id") var orderId: Int? = null

)