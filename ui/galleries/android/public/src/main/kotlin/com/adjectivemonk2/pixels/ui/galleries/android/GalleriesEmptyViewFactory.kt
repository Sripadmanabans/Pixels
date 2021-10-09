package com.adjectivemonk2.pixels.ui.galleries.android

import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Empty
import com.squareup.workflow1.ui.compose.ComposeViewFactory
import kotlin.reflect.KClass

public abstract class GalleriesEmptyViewFactory : ComposeViewFactory<Empty>() {
  override val type: KClass<in Empty> = Empty::class
}
