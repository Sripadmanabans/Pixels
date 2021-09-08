package com.adjectivemonk2.pixels.ui.galleries.common

import com.adjectivemonk2.pixels.model.gallery.Gallery
import com.squareup.workflow1.Workflow

public interface GalleriesWorkflow : Workflow<Unit, Unit, GalleriesScreen>

public sealed class GalleriesScreen {

  public data class Info(val galleries: List<Gallery>) : GalleriesScreen()

  public object Loading : GalleriesScreen()

  public data class Error(val message: String) : GalleriesScreen()
}
