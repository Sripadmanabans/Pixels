package com.adjectivemonk2.pixels.database.gallery.impl

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant
import javax.inject.Inject

public class InstantColumnAdapter @Inject constructor() : ColumnAdapter<Instant, String> {
  override fun decode(databaseValue: String): Instant {
    return Instant.parse(databaseValue)
  }

  override fun encode(value: Instant): String {
    return value.toString()
  }
}
