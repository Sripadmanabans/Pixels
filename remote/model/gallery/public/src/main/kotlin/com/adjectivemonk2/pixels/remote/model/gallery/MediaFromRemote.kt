@file:UseSerializers(InstantSerializerAdapter::class)

package com.adjectivemonk2.pixels.remote.model.gallery

import com.adjectivemonk2.pixels.remote.model.core.InstantSerializerAdapter
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
public sealed interface MediaFromRemote

@Serializable
@SerialName("image/jpeg")
public data class JpegFromRemote(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String?,
  @SerialName("description") val description: String?,
  @SerialName("datetime") val dateTime: Instant,
  @SerialName("animated") val animated: Boolean,
  @SerialName("width") val width: Long,
  @SerialName("height") val height: Long,
  @SerialName("size") val size: Long,
  @SerialName("views") val views: Long,
  @SerialName("bandwidth") val bandwidth: Long,
  @SerialName("vote") val vote: String?,
  @SerialName("favorite") val favorite: Boolean,
  @SerialName("nsfw") val nsfw: Boolean?,
  @SerialName("section") val section: String?,
  @SerialName("account_url") val accountUrl: String?,
  @SerialName("account_id") val accountId: String?,
  @SerialName("is_most_viral") val isMostViral: Boolean?,
  @SerialName("has_sound") val hasSound: Boolean,
  @SerialName("tags") val tags: List<TagFromRemote>,
  @SerialName("edited") val edited: String,
  @SerialName("in_gallery") val inGallery: Boolean,
  @SerialName("link") val link: String,
) : MediaFromRemote

@Serializable
@SerialName("image/png")
public data class PngFromRemote(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String?,
  @SerialName("description") val description: String?,
  @SerialName("datetime") val dateTime: Instant,
  @SerialName("animated") val animated: Boolean,
  @SerialName("width") val width: Long,
  @SerialName("height") val height: Long,
  @SerialName("size") val size: Long,
  @SerialName("views") val views: Long,
  @SerialName("bandwidth") val bandwidth: Long,
  @SerialName("vote") val vote: String?,
  @SerialName("favorite") val favorite: Boolean,
  @SerialName("nsfw") val nsfw: Boolean?,
  @SerialName("section") val section: String?,
  @SerialName("account_url") val accountUrl: String?,
  @SerialName("account_id") val accountId: String?,
  @SerialName("is_most_viral") val isMostViral: Boolean?,
  @SerialName("has_sound") val hasSound: Boolean,
  @SerialName("tags") val tags: List<TagFromRemote>,
  @SerialName("edited") val edited: String,
  @SerialName("in_gallery") val inGallery: Boolean,
  @SerialName("link") val link: String,
) : MediaFromRemote

@Serializable
@SerialName("image/gif")
public data class GifFromRemote(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String?,
  @SerialName("description") val description: String?,
  @SerialName("datetime") val dateTime: Instant,
  @SerialName("animated") val animated: Boolean,
  @SerialName("width") val width: Long,
  @SerialName("height") val height: Long,
  @SerialName("size") val size: Long,
  @SerialName("views") val views: Long,
  @SerialName("bandwidth") val bandwidth: Long,
  @SerialName("vote") val vote: String?,
  @SerialName("favorite") val favorite: Boolean,
  @SerialName("nsfw") val nsfw: Boolean?,
  @SerialName("section") val section: String?,
  @SerialName("account_url") val accountUrl: String?,
  @SerialName("account_id") val accountId: String?,
  @SerialName("is_most_viral") val isMostViral: Boolean?,
  @SerialName("has_sound") val hasSound: Boolean,
  @SerialName("tags") val tags: List<TagFromRemote>,
  @SerialName("edited") val edited: String,
  @SerialName("in_gallery") val inGallery: Boolean,
  @SerialName("link") val link: String,
) : MediaFromRemote

@Serializable
@SerialName("video/mp4")
public data class Mp4FromRemote(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String?,
  @SerialName("description") val description: String?,
  @SerialName("datetime") val dateTime: Instant,
  @SerialName("animated") val animated: Boolean,
  @SerialName("width") val width: Long,
  @SerialName("height") val height: Long,
  @SerialName("size") val size: Long,
  @SerialName("views") val views: Long,
  @SerialName("bandwidth") val bandwidth: Long,
  @SerialName("vote") val vote: String?,
  @SerialName("favorite") val favorite: Boolean,
  @SerialName("nsfw") val nsfw: Boolean?,
  @SerialName("section") val section: String?,
  @SerialName("account_url") val accountUrl: String?,
  @SerialName("account_id") val accountId: String?,
  @SerialName("is_most_viral") val isMostViral: Boolean?,
  @SerialName("has_sound") val hasSound: Boolean,
  @SerialName("tags") val tags: List<TagFromRemote>,
  @SerialName("edited") val edited: String,
  @SerialName("in_gallery") val inGallery: Boolean,
  @SerialName("link") val link: String,
  @SerialName("mp4_size") val mp4Size: Long,
  @SerialName("hls") val hls: String,
  @SerialName("processing") val processing: Processing,
) : MediaFromRemote

@Serializable
public data object Unknown : MediaFromRemote

@Serializable
public data class Processing(
  @SerialName("status") val status: String,
)
