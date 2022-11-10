package com.adjectivemonk2.pixels.model.gallery

public sealed interface Media {
  public val id: String
  public val title: String?
  public val description: String?
  public val width: Long
  public val height: Long
  public val size: Long
  public val views: Long
  public val favorite: Boolean
  public val nsfw: Boolean?
  public val isMostViral: Boolean?
  public val tags: List<Tag>
  public val edited: String
  public val link: String
}

public data class Jpeg(
  override val id: String,
  override val title: String?,
  override val description: String?,
  override val width: Long,
  override val height: Long,
  override val size: Long,
  override val views: Long,
  override val favorite: Boolean,
  override val nsfw: Boolean?,
  override val isMostViral: Boolean?,
  override val tags: List<Tag>,
  override val edited: String,
  override val link: String,
) : Media

public data class Png(
  override val id: String,
  override val title: String?,
  override val description: String?,
  override val width: Long,
  override val height: Long,
  override val size: Long,
  override val views: Long,
  override val favorite: Boolean,
  override val nsfw: Boolean?,
  override val isMostViral: Boolean?,
  override val tags: List<Tag>,
  override val edited: String,
  override val link: String,
) : Media

public data class Gif(
  override val id: String,
  override val title: String?,
  override val description: String?,
  override val width: Long,
  override val height: Long,
  override val size: Long,
  override val views: Long,
  override val favorite: Boolean,
  override val nsfw: Boolean?,
  override val isMostViral: Boolean?,
  override val tags: List<Tag>,
  override val edited: String,
  override val link: String,
  val hasSound: Boolean,
) : Media

public data class Mp4(
  override val id: String,
  override val title: String?,
  override val description: String?,
  override val width: Long,
  override val height: Long,
  override val size: Long,
  override val views: Long,
  override val favorite: Boolean,
  override val nsfw: Boolean?,
  override val isMostViral: Boolean?,
  override val tags: List<Tag>,
  override val edited: String,
  override val link: String,
  val hasSound: Boolean,
  val mp4Size: Long,
  val hls: String,
) : Media
