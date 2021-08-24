package com.adjectivemonk2.pixels.model.gallery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.zacsweers.moshix.sealed.annotations.DefaultObject
import dev.zacsweers.moshix.sealed.annotations.TypeLabel

@JsonClass(generateAdapter = true, generator = "sealed:type")
public sealed class Media

@JsonClass(generateAdapter = true)
@TypeLabel(label = "image/jpeg")
public data class Jpeg(
  @Json(name = "id") val id: String,
  @Json(name = "title") val title: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "datetime") val dateTime: Long,
  @Json(name = "animated") val animated: Boolean,
  @Json(name = "width") val width: Long,
  @Json(name = "height") val height: Long,
  @Json(name = "size") val size: Long,
  @Json(name = "views") val views: Long,
  @Json(name = "bandwidth") val bandwidth: Long,
  @Json(name = "vote") val vote: String?,
  @Json(name = "favorite") val favorite: Boolean,
  @Json(name = "nsfw") val nsfw: Boolean?,
  @Json(name = "section") val section: String?,
  @Json(name = "account_url") val accountUrl: String?,
  @Json(name = "account_id") val accountId: String?,
  @Json(name = "is_most_viral") val isMostViral: Boolean?,
  @Json(name = "has_sound") val hasSound: Boolean,
  @Json(name = "tags") val tags: List<Tag>,
  @Json(name = "edited") val edited: String,
  @Json(name = "in_gallery") val inGallery: Boolean,
  @Json(name = "link") val link: String,
) : Media()

@JsonClass(generateAdapter = true)
@TypeLabel(label = "image/png")
public data class Png(
  @Json(name = "id") val id: String,
  @Json(name = "title") val title: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "datetime") val dateTime: Long,
  @Json(name = "animated") val animated: Boolean,
  @Json(name = "width") val width: Long,
  @Json(name = "height") val height: Long,
  @Json(name = "size") val size: Long,
  @Json(name = "views") val views: Long,
  @Json(name = "bandwidth") val bandwidth: Long,
  @Json(name = "vote") val vote: String?,
  @Json(name = "favorite") val favorite: Boolean,
  @Json(name = "nsfw") val nsfw: Boolean?,
  @Json(name = "section") val section: String?,
  @Json(name = "account_url") val accountUrl: String?,
  @Json(name = "account_id") val accountId: String?,
  @Json(name = "is_most_viral") val isMostViral: Boolean?,
  @Json(name = "has_sound") val hasSound: Boolean,
  @Json(name = "tags") val tags: List<Tag>,
  @Json(name = "edited") val edited: String,
  @Json(name = "in_gallery") val inGallery: Boolean,
  @Json(name = "link") val link: String,
) : Media()

@JsonClass(generateAdapter = true)
@TypeLabel(label = "image/gif")
public data class Gif(
  @Json(name = "id") val id: String,
  @Json(name = "title") val title: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "datetime") val dateTime: Long,
  @Json(name = "animated") val animated: Boolean,
  @Json(name = "width") val width: Long,
  @Json(name = "height") val height: Long,
  @Json(name = "size") val size: Long,
  @Json(name = "views") val views: Long,
  @Json(name = "bandwidth") val bandwidth: Long,
  @Json(name = "vote") val vote: String?,
  @Json(name = "favorite") val favorite: Boolean,
  @Json(name = "nsfw") val nsfw: Boolean?,
  @Json(name = "section") val section: String?,
  @Json(name = "account_url") val accountUrl: String?,
  @Json(name = "account_id") val accountId: String?,
  @Json(name = "is_most_viral") val isMostViral: Boolean?,
  @Json(name = "has_sound") val hasSound: Boolean,
  @Json(name = "tags") val tags: List<Tag>,
  @Json(name = "edited") val edited: String,
  @Json(name = "in_gallery") val inGallery: Boolean,
  @Json(name = "link") val link: String,
) : Media()

@JsonClass(generateAdapter = true)
@TypeLabel("video/mp4")
public data class Mp4(
  @Json(name = "id") val id: String,
  @Json(name = "title") val title: String?,
  @Json(name = "description") val description: String?,
  @Json(name = "datetime") val dateTime: Long,
  @Json(name = "animated") val animated: Boolean,
  @Json(name = "width") val width: Long,
  @Json(name = "height") val height: Long,
  @Json(name = "size") val size: Long,
  @Json(name = "views") val views: Long,
  @Json(name = "bandwidth") val bandwidth: Long,
  @Json(name = "vote") val vote: String?,
  @Json(name = "favorite") val favorite: Boolean,
  @Json(name = "nsfw") val nsfw: Boolean?,
  @Json(name = "section") val section: String?,
  @Json(name = "account_url") val accountUrl: String?,
  @Json(name = "account_id") val accountId: String?,
  @Json(name = "is_most_viral") val isMostViral: Boolean?,
  @Json(name = "has_sound") val hasSound: Boolean,
  @Json(name = "tags") val tags: List<Tag>,
  @Json(name = "edited") val edited: String,
  @Json(name = "in_gallery") val inGallery: Boolean,
  @Json(name = "link") val link: String,
  @Json(name = "mp4_size") val mp4Size: Long,
  @Json(name = "mp4") val mp4: String,
  @Json(name = "gifv") val gifv: String,
  @Json(name = "hls") val hls: String,
  @Json(name = "processing") val processing: Processing,
) : Media()

@DefaultObject
public object Unknown : Media()

@JsonClass(generateAdapter = true)
public data class Processing(
  @Json(name = "status") val status: String,
)
