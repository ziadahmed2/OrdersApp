package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class PromoCodeDto(
  
  @SerializedName("code") var code: String? = null,
  @SerializedName("cart_discount") var cartDiscount: Double? = null,
  @SerializedName("delivery_discount") var deliveryDiscount: Int? = null,
  @SerializedName("total_discount") var totalDiscount: Double? = null
)