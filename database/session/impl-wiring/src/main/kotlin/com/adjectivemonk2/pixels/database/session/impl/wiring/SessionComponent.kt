package com.adjectivemonk2.pixels.database.session.impl.wiring

import com.adjectivemonk2.pixels.dagger.scope.AppScope
import com.adjectivemonk2.pixels.database.session.SessionLocalDataSource
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(AppScope::class)
public interface SessionComponent {
  public fun sessionLocalDataSource(): SessionLocalDataSource
}
