package com.adjectivemonk2.pixels.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.adjectivemonk2.pixels.database.PixelsDb
import com.adjectivemonk2.pixels.database.session.Session
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.adjectivemonk2.pixels.database.session.PixelsDb as PixelsDb0

@Module
@ContributesTo(AppScope::class)
public interface SqlModule {

  @Binds
  public fun pixelsDb(pixelsDb: PixelsDb): PixelsDb0

  public companion object {

    @Provides
    @SingleIn(AppScope::class)
    public fun sqlDriver(application: Application): SqlDriver {
      return AndroidSqliteDriver(PixelsDb.Schema, application, "pixels.db")
    }

    @Provides
    @SingleIn(AppScope::class)
    public fun pixelsDb(
      sqlDriver: SqlDriver,
      sessionAdapter: Session.Adapter,
    ): PixelsDb {
      return PixelsDb(sqlDriver, sessionAdapter)
    }
  }
}
