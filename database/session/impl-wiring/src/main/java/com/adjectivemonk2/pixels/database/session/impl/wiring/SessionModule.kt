package com.adjectivemonk2.pixels.database.session.impl.wiring

import com.adjectivemonk2.pixels.database.session.Session
import com.adjectivemonk2.pixels.database.session.impl.DurationColumnAdapter
import com.adjectivemonk2.scope.AppScope
import com.adjectivemonk2.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(AppScope::class)
public object SessionModule {

  @Provides @SingleIn(AppScope::class)
  public fun sessionAdapter(durationColumnAdapter: DurationColumnAdapter): Session.Adapter {
    return Session.Adapter(expiresInAdapter = durationColumnAdapter)
  }
}
