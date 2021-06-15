package com.adjectivemonk2.pixels.di

import android.app.Application
import com.adjectivemonk2.pixels.database.PixelsDb
import com.adjectivemonk2.pixels.database.session.Session
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.adjectivemonk2.pixels.database.session.PixelsDb as PixelsDb0

@Module
@InstallIn(SingletonComponent::class)
abstract class SqlModule {

  @Binds abstract fun pixelsDb(pixelsDb: PixelsDb): PixelsDb0

  companion object {

    @Provides @Singleton fun sqlDriver(application: Application): SqlDriver {
      return AndroidSqliteDriver(PixelsDb.Schema, application, "pixels.db")
    }

    @Provides @Singleton fun pixelsDb(
      sqlDriver: SqlDriver,
      sessionAdapter: Session.Adapter,
    ): PixelsDb {
      return PixelsDb(sqlDriver, sessionAdapter)
    }
  }
}
