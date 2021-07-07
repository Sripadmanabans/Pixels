package com.adjectivemonk2.network.gallery

public interface GalleryRepository {
  public suspend fun getGallery(
    section: Section = Section.HOT,
    sort: Sort = Sort.VIRAL,
    window: Window = Window.DAY,
    page: Int = 0,
  )
}
