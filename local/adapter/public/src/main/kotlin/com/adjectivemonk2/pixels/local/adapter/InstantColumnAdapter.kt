package com.adjectivemonk2.pixels.local.adapter

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

public interface InstantColumnAdapter : ColumnAdapter<Instant, String>
