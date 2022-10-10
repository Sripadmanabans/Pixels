package com.adjectivemonk2.pixels.database.session.impl

import app.cash.sqldelight.ColumnAdapter
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

public class DurationColumnAdapter @Inject constructor() : ColumnAdapter<Duration, Long> {
  override fun decode(databaseValue: Long): Duration {
    return databaseValue.toDuration(DurationUnit.SECONDS)
  }

  override fun encode(value: Duration): Long {
    return value.toLong(DurationUnit.SECONDS)
  }
}
