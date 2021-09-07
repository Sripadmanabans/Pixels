package com.adjectivemonk2.pixels.ui.galleries.android

import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Error
import com.squareup.workflow1.ui.compose.ComposeViewFactory
import kotlin.reflect.KClass

public abstract class GalleriesErrorViewFactory : ComposeViewFactory<Error>() {
  override val type: KClass<in Error> = Error::class
}
