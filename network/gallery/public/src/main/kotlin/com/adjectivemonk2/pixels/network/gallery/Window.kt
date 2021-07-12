package com.adjectivemonk2.pixels.network.gallery

@JvmInline
public value class Window(public val param: String) {
  public companion object {
    public val DAY: Window = Window("day")
    public val WEEK: Window = Window("week")
    public val MONTH: Window = Window("month")
    public val YEAR: Window = Window("year")
    public val ALL: Window = Window("all")
  }
}
