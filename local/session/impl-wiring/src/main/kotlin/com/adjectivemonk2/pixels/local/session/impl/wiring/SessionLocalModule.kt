package com.adjectivemonk2.pixels.local.session.impl.wiring

import com.adjectivemonk2.pixels.local.session.Session
import com.adjectivemonk2.pixels.local.session.impl.DurationColumnAdapter
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(AppScope::class)
public object SessionLocalModule {

  @Provides
  @SingleIn(AppScope::class)
  public fun sessionAdapter(durationColumnAdapter: DurationColumnAdapter): Session.Adapter {
    return Session.Adapter(expiresInAdapter = durationColumnAdapter)
  }
}
