package com.adjectivemonk2.pixels.remote.core.impl.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import kotlinx.datetime.Instant
import javax.inject.Inject

public class InstantAdapter @Inject constructor() {

  @FromJson
  public fun fromJson(dateTime: Long): Instant = Instant.fromEpochMilliseconds(dateTime)

  @ToJson
  public fun toJson(dateTime: Instant): Long = dateTime.toEpochMilliseconds()
}
