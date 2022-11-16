package com.adjectivemonk2.pixels.remote.gallery.impl.converter

import com.adjectivemonk2.pixels.model.gallery.Tag
import com.adjectivemonk2.pixels.remote.model.gallery.TagFromRemote
import javax.inject.Inject

public class TagConverter @Inject constructor() {
  internal fun convert(tagFromRemote: TagFromRemote): Tag {
    return Tag(
      name = tagFromRemote.name,
      displayName = tagFromRemote.displayName,
      followers = tagFromRemote.followers,
      totalItems = tagFromRemote.totalItems,
      isPromoted = tagFromRemote.isPromoted,
    )
  }
}
