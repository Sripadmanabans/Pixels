package com.adjectivemonk2.pixels.network.gallery

@JvmInline
public value class Section(public val param: String) {
  public companion object {
    public val HOT: Section = Section("hot")
    public val TOP: Section = Section("top")
    public val USER: Section = Section("user")
  }
}
