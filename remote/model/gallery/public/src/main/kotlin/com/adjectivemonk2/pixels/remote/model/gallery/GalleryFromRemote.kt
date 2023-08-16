@file:UseSerializers(InstantSerializerAdapter::class)

package com.adjectivemonk2.pixels.remote.model.gallery

import com.adjectivemonk2.pixels.remote.model.core.InstantSerializerAdapter
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
public data class GalleryFromRemote(
  @SerialName("id") val id: String,
  @SerialName("title") val title: String,
  @SerialName("description") val description: String?,
  @SerialName("datetime") val dateTime: Instant,
  @SerialName("cover") val cover: String?,
  @SerialName("cover_width") val coverWidth: Int?,
  @SerialName("cover_height") val coverHeight: Int?,
  @SerialName("account_url") val accountUrl: String,
  @SerialName("account_id") val accountId: Long,
  @SerialName("privacy") val privacy: String?,
  @SerialName("layout") val layout: String?,
  @SerialName("views") val views: Long,
  @SerialName("link") val link: String,
  @SerialName("ups") val ups: Long,
  @SerialName("downs") val downs: Long,
  @SerialName("points") val points: Long,
  @SerialName("score") val score: Long,
  @SerialName("is_album") val isAlbum: Boolean,
  @SerialName("vote") val vote: String?,
  @SerialName("favorite") val favorite: Boolean,
  @SerialName("nsfw") val nsfw: Boolean,
  @SerialName("section") val section: String,
  @SerialName("comment_count") val commentCount: Long,
  @SerialName("favorite_count") val favoriteCount: Long,
  @SerialName("topic") val topic: String?,
  @SerialName("topic_id") val topicId: String?,
  @SerialName("images_count") val imagesCount: Long?,
  @SerialName("in_gallery") val inGallery: Boolean,
  @SerialName("tags") val tags: List<TagFromRemote>,
  @SerialName("in_most_viral") val inMostViral: Boolean,
  @SerialName("include_album_ads") val includeAlbumAds: Boolean?,
  @SerialName("images") val media: List<MediaFromRemote>?,
)
