package com.adjectivemonk2.pixels.remote.model.gallery.impl.wiring

import com.adjectivemonk2.pixels.remote.model.gallery.GifFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.JpegFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.MediaFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.Mp4FromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.PngFromRemote
import com.adjectivemonk2.pixels.remote.model.gallery.Unknown
import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Module
@ContributesTo(AppScope::class)
public object GalleryRemoteSerializerModule {

  @Provides
  @IntoSet
  public fun module(): SerializersModule {
    return SerializersModule {
      polymorphic(MediaFromRemote::class) {
        subclass(JpegFromRemote::class)
        subclass(PngFromRemote::class)
        subclass(GifFromRemote::class)
        subclass(Mp4FromRemote::class)
        defaultDeserializer { Unknown.serializer() }
      }
    }
  }
}
