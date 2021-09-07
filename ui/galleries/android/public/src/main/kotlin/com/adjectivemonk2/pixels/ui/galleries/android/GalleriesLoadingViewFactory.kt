package com.adjectivemonk2.pixels.ui.galleries.android

import com.adjectivemonk2.pixels.ui.galleries.common.GalleriesScreen.Loading
import com.squareup.workflow1.ui.compose.ComposeViewFactory
import kotlin.reflect.KClass

public abstract class GalleriesLoadingViewFactory : ComposeViewFactory<Loading>() {
  override val type: KClass<in Loading> = Loading::class
}
