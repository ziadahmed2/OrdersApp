package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class DeliveryDto(
  @SerializedName("delivery_fee") var deliveryFee: Int? = null
)