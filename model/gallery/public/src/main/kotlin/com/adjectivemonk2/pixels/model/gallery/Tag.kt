package com.adjectivemonk2.pixels.model.gallery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class Tag(
  @Json(name = "name") val name: String,
  @Json(name = "display_name") val displayName: String,
  @Json(name = "followers") val followers: Long,
  @Json(name = "total_items") val totalItems: Long,
  @Json(name = "following") val following: Boolean,
  @Json(name = "is_whitelisted") val isWhitelisted: Boolean,
  @Json(name = "background_hash") val backgroundHash: String,
  @Json(name = "thumbnail_hash") val thumbnailHash: String?,
  @Json(name = "accent") val accent: String?,
  @Json(name = "background_is_animated") val backgroundIsAnimated: Boolean,
  @Json(name = "thumbnail_is_animated") val thumbnailIsAnimated: Boolean,
  @Json(name = "is_promoted") val isPromoted: Boolean,
  @Json(name = "description") val description: String?,
  @Json(name = "logo_hash") val logoHash: String?,
  @Json(name = "logo_destination_url") val logoDestinationUrl: String?,
)
