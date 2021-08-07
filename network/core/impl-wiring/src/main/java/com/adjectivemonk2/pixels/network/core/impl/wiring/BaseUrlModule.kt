package com.adjectivemonk2.pixels.network.core.impl.wiring

import com.adjectivemonk2.pixels.scope.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@ContributesTo(AppScope::class)
public object BaseUrlModule {

  @Provides public fun baseUrl(): HttpUrl {
    return "https://api.imgur.com/3/".toHttpUrl()
  }
}
