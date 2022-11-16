package com.adjectivemonk2.pixels.local.session.model

import kotlin.time.Duration

public data class SessionInfo(
  val accessToken: String,
  val refreshToken: String,
  val expiresIn: Duration,
)
