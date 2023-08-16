package com.adjectivemonk2.pixels.remote.model.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Data<T>(
  @SerialName("data") val data: T,
  @SerialName("success") val success: Boolean,
  @SerialName("status") val status: Int,
)
