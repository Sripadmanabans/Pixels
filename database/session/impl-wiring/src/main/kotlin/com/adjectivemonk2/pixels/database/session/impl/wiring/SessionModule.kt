package com.adjectivemonk2.pixels.database.session.impl.wiring

import com.adjectivemonk2.pixels.database.session.SessionLocalDataSource

public interface SessionComponent {
  public fun sessionLocalDataSource(): SessionLocalDataSource
}
