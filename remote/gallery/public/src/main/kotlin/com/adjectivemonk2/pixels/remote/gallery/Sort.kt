package com.adjectivemonk2.pixels.remote.gallery

@JvmInline
public value class Sort(public val param: String) {
  public companion object {
    public val VIRAL: Sort = Sort("viral")
    public val TOP: Sort = Sort("top")
    public val TIME: Sort = Sort("time")
    public val RISING: Sort = Sort("rising")
  }
}
