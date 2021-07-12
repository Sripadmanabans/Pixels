package com.adjectivemonk2.pixels.network.gallery.impl

import retrofit2.http.GET
import retrofit2.http.Path

public interface GalleryService {

  @GET("gallery/{section}/{sort}/{window}/{page}")
  public suspend fun getGallery(
    @Path("section") section: String,
    @Path("sort") sort: String,
    @Path("window") window: String,
    @Path("page") page: Int,
  )
}
