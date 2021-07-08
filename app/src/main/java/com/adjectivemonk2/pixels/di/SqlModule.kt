package com.adjectivemonk2.pixels.di

import android.app.Application
import com.adjectivemonk2.pixels.database.PixelsDb
import com.adjectivemonk2.pixels.database.session.Session
import com.adjectivemonk2.scope.AppScope
import com.adjectivemonk2.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.adjectivemonk2.pixels.database.session.PixelsDb as PixelsDb0

@Module
@ContributesTo(AppScope::class)
abstract class SqlModule {

  @Binds abstract fun pixelsDb(pixelsDb: PixelsDb): PixelsDb0

  companion object {

    @Provides @SingleIn(AppScope::class) fun sqlDriver(application: Application): SqlDriver {
      return AndroidSqliteDriver(PixelsDb.Schema, application, "pixels.db")
    }

    @Provides @SingleIn(AppScope::class) fun pixelsDb(
      sqlDriver: SqlDriver,
      sessionAdapter: Session.Adapter,
    ): PixelsDb {
      return PixelsDb(sqlDriver, sessionAdapter)
    }
  }
}
