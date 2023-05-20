package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class ItemsDto(
  
  @SerializedName("id") var id: Int? = null,
  @SerializedName("package_id") var packageId: Int? = null,
  @SerializedName("sku") var sku: String? = null,
  @SerializedName("ordered_quantity") var orderedQuantity: Int? = null,
  @SerializedName("price") var price: Double? = null,
  @SerializedName("discounted_quantity") var discountedQuantity: Int? = null,
  @SerializedName("discount") var discount: Int? = null,
  @SerializedName("unit_price") var unitPrice: Double? = null,
  @SerializedName("original_quantity") var originalQuantity: Int? = null,
  @SerializedName("status") var status: String? = null,
  @SerializedName("status_code") var statusCode: Int? = null,
  @SerializedName("image") var image: String? = null,
  @SerializedName("title") var title: String? = null,
  @SerializedName("consumable_display") var consumableDisplay: String? = null,
  @SerializedName("quantity") var quantity: String? = null,
  @SerializedName("sub_title") var subTitle: String? = null
)