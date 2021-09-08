package com.adjectivemonk2.pixels.di

import com.adjectivemonk2.pixels.scope.ActivityScope
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.workflow1.ui.ViewEnvironment
import com.squareup.workflow1.ui.ViewRegistry
import com.squareup.workflow1.ui.plus
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(ActivityScope::class)
object ActivityModule {

  @Provides fun viewEnvironment(
    viewRegistries: Set<@JvmSuppressWildcards ViewRegistry>
  ): ViewEnvironment {
    val registry = viewRegistries.reduce { acc, viewRegistry -> acc + viewRegistry }
    return ViewEnvironment(mapOf(ViewRegistry to registry))
  }
}
