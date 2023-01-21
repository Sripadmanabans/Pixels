package com.adjectivemonk2.pixels.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.adjectivemonk2.pixels.local.PixelsDb
import com.adjectivemonk2.pixels.local.gallery.GalleryFromDb
import com.adjectivemonk2.pixels.local.session.Session
import com.adjectivemonk2.pixels.scope.AppScope
import com.adjectivemonk2.pixels.scope.SingleIn
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.adjectivemonk2.pixels.local.gallery.PixelsDb as PixelsGalleryDb
import com.adjectivemonk2.pixels.local.session.PixelsDb as PixelsSessionDb

@Module
@ContributesTo(AppScope::class)
public interface LocalModule {

  @Binds public fun pixelsSessionDb(pixelsDb: PixelsDb): PixelsSessionDb

  @Binds public fun pixelsGalleryDb(pixelsDb: PixelsDb): PixelsGalleryDb

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
      galleryDbAdapter: GalleryFromDb.Adapter,
      sessionAdapter: Session.Adapter,
    ): PixelsDb {
      return PixelsDb(sqlDriver, galleryDbAdapter, sessionAdapter)
    }
  }
}
