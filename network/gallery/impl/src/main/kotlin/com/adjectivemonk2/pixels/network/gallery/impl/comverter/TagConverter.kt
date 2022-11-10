package com.adjectivemonk2.pixels.network.gallery.impl.comverter

import com.adjectivemonk2.pixels.model.gallery.Tag
import com.adjectivemonk2.pixels.network.model.gallery.TagFromNetwork
import javax.inject.Inject

public class TagConverter @Inject constructor() {
  internal fun convert(tagFromNetwork: TagFromNetwork): Tag {
    return Tag(
      name = tagFromNetwork.name,
      displayName = tagFromNetwork.displayName,
      followers = tagFromNetwork.followers,
      totalItems = tagFromNetwork.totalItems,
      isPromoted = tagFromNetwork.isPromoted,
    )
  }
}
