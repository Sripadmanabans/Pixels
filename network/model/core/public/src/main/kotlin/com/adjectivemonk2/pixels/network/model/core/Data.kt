package com.adjectivemonk2.pixels.network.model.core

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class Data<T>(
  @Json(name = "data") val data: T,
  @Json(name = "success") val success: Boolean,
  @Json(name = "status") val status: Int,
)
