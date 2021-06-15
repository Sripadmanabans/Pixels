package com.adjectivemonk2.pixels.database.session.impl.wiring

import com.adjectivemonk2.pixels.database.session.Session
import com.adjectivemonk2.pixels.database.session.SessionLocalDataSource
import com.adjectivemonk2.pixels.database.session.impl.DurationColumnAdapter
import com.adjectivemonk2.pixels.database.session.impl.SessionLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public abstract class SessionModule {

  @Binds
  public abstract fun dataSource(dataSource: SessionLocalDataSourceImpl): SessionLocalDataSource

  public companion object {

    @Provides
    @Singleton
    public fun sessionAdapter(durationColumnAdapter: DurationColumnAdapter): Session.Adapter {
      return Session.Adapter(expiresInAdapter = durationColumnAdapter)
    }
  }
}
