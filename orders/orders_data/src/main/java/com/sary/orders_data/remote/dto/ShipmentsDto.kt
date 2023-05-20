package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShipmentsDto(
  
  @SerializedName("id") var id: Int? = null,
  @SerializedName("shipment_code") var shipmentCode: String? = null,
  @SerializedName("branch_id") var branchId: Int? = null,
  @SerializedName("delivery_system") var deliverySystem: Int? = null,
  @SerializedName("delivery_date") var deliveryDate: String? = null,
  @SerializedName("interval_start_time") var intervalStartTime: String? = null,
  @SerializedName("interval_end_time") var intervalEndTime: String? = null,
  @SerializedName("interval_start_time_12") var intervalStartTime12: String? = null,
  @SerializedName("interval_end_time_12") var intervalEndTime12: String? = null,
  @SerializedName("delivery_day") var deliveryDay: String? = null,
  @SerializedName("delivery_current_status") var deliveryCurrentStatus: String? = null,
  @SerializedName("delivery_status_code") var deliveryStatusCode: Int? = null,
  @SerializedName("items") var items: ArrayList<ItemsDto> = arrayListOf(),
  @SerializedName("code") var code: String? = null,
  @SerializedName("status_header_message") var statusHeaderMessage: String? = null,
  @SerializedName("status_detailed_message") var statusDetailedMessage: String? = null,
  @SerializedName("grand_total") var grandTotal: String? = null,
  @SerializedName("shipment_total") var shipmentTotal: String? = null,
  @SerializedName("delivery") var delivery: DeliveryDto? = null,
  @SerializedName("promocode") var promocode: PromoCodeDto? = null,
  @SerializedName("is_rescheduled") var isRescheduled: Boolean? = null,
  @SerializedName("delivered_at") var deliveredAt: String? = null,
  @SerializedName("driver_id") var driverId: String? = null,
  @SerializedName("driver_name") var driverName: String? = null
)