package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class DeliveryItemsDto(
  
  @SerializedName("items_list") var itemsList: ArrayList<String> = arrayListOf(),
  @SerializedName("remaining_items") var remainingItems: Int? = null

)