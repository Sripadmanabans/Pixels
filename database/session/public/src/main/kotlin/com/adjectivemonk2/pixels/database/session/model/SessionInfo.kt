package com.adjectivemonk2.pixels.database.session.model

import kotlin.time.Duration

public data class SessionInfo(
  val accessToken: String,
  val refreshToken: String,
  val expiresIn: Duration,
)
