package com.adjectivemonk2.pixels.remote.model.gallery

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TagFromRemote(
  @SerialName("name") val name: String,
  @SerialName("display_name") val displayName: String,
  @SerialName("followers") val followers: Long,
  @SerialName("total_items") val totalItems: Long,
  @SerialName("following") val following: Boolean,
  @SerialName("is_whitelisted") val isWhitelisted: Boolean,
  @SerialName("background_hash") val backgroundHash: String,
  @SerialName("thumbnail_hash") val thumbnailHash: String?,
  @SerialName("accent") val accent: String?,
  @SerialName("background_is_animated") val backgroundIsAnimated: Boolean,
  @SerialName("thumbnail_is_animated") val thumbnailIsAnimated: Boolean,
  @SerialName("is_promoted") val isPromoted: Boolean,
  @SerialName("description") val description: String?,
  @SerialName("logo_hash") val logoHash: String?,
  @SerialName("logo_destination_url") val logoDestinationUrl: String?,
)
