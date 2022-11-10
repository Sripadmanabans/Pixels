package com.adjectivemonk2.pixels.network.model.gallery

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.Instant

@JsonClass(generateAdapter = true)
public data class GalleryFromNetwork(
  @Json(name = "id") val id: String,
  @Json(name = "title") val title: String,
  @Json(name = "description") val description: String?,
  @Json(name = "datetime") val dateTime: Instant,
  @Json(name = "cover") val cover: String?,
  @Json(name = "cover_width") val coverWidth: Int?,
  @Json(name = "cover_height") val coverHeight: Int?,
  @Json(name = "account_url") val accountUrl: String,
  @Json(name = "account_id") val accountId: Long,
  @Json(name = "privacy") val privacy: String?,
  @Json(name = "layout") val layout: String?,
  @Json(name = "views") val views: Long,
  @Json(name = "link") val link: String,
  @Json(name = "ups") val ups: Long,
  @Json(name = "downs") val downs: Long,
  @Json(name = "points") val points: Long,
  @Json(name = "score") val score: Long,
  @Json(name = "is_album") val isAlbum: Boolean,
  @Json(name = "vote") val vote: String?,
  @Json(name = "favorite") val favorite: Boolean,
  @Json(name = "nsfw") val nsfw: Boolean,
  @Json(name = "section") val section: String,
  @Json(name = "comment_count") val commentCount: Long,
  @Json(name = "favorite_count") val favoriteCount: Long,
  @Json(name = "topic") val topic: String?,
  @Json(name = "topic_id") val topicId: String?,
  @Json(name = "images_count") val imagesCount: Long?,
  @Json(name = "in_gallery") val inGallery: Boolean,
  @Json(name = "tags") val tags: List<TagFromNetwork>,
  @Json(name = "in_most_viral") val inMostViral: Boolean,
  @Json(name = "include_album_ads") val includeAlbumAds: Boolean?,
  @Json(name = "images") val media: List<MediaFromNetwork>?,
)
