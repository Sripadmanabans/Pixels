package com.adjectivemonk2.pixels.ui.galleries.android

import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Info
import com.squareup.workflow1.ui.compose.ComposeViewFactory
import kotlin.reflect.KClass

public abstract class GalleriesInfoViewFactory : ComposeViewFactory<Info>() {
  override val type: KClass<in Info> = Info::class
}
