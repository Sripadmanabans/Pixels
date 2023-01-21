package com.adjectivemonk2.pixels.local.adapter

import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import javax.inject.Inject

@ContributesBinding(scope = AppScope::class)
public class InstantColumnAdapterImpl @Inject constructor() : InstantColumnAdapter {
  override fun decode(databaseValue: String): Instant {
    return databaseValue.toInstant()
  }

  override fun encode(value: Instant): String {
    return value.toString()
  }
}
