package com.sary.orders_data.remote.dto

import com.google.gson.annotations.SerializedName

data class OrdersResponseDto(
  
  @SerializedName("next") var next: String? = null,
  @SerializedName("previous") var previous: String? = null,
  @SerializedName("count") var count: Int? = null,
  @SerializedName("num_pages") var numPages: Int? = null,
  @SerializedName("current_page") var currentPage: Int? = null,
  @SerializedName("result") var result: ArrayList<ResultDto> = arrayListOf(),
  @SerializedName("status") var status: Boolean? = null,
  @SerializedName("other") var other: String? = null,
  @SerializedName("message") var message: String? = null
)