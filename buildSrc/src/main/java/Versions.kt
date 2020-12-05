/**
 * Contains version info.
 */
object Versions {

  /**
   * Contains version info for androidx.
   */
  object AndroidX {
    const val activity = "1.2.0-alpha08"
    const val appcompat = "1.3.0-alpha02"
    const val core = "1.5.0-alpha05"
    const val hilt = "1.0.0-alpha02"
    const val fragment = "1.3.0-alpha06"
    const val lifecycle = "2.3.0-beta01"
    const val room = "2.3.0-alpha01"

    object Compose {
      const val core = "1.0.0-alpha08"
      const val navigation = "1.0.0-alpha03"
    }

    val compose = Compose

    /**
     * Contains version info for androidx's tests.
     */
    object Test {
      const val espresso = "3.3.0-rc01"
      const val junit = "1.1.2-rc01"
      const val orchestrator = "1.3.0-beta01"
      const val rules = "1.3.0-rc01"
      const val runner = "1.3.0-rc01"
    }

    val test = Test
  }

  val androidX = AndroidX

  const val ben = "0.36.0"

  object Chris {
    const val coil = "0.4.0"
  }

  val chris = Chris

  const val detekt = "1.15.0-RC1"

  /**
   * Contains version info for google.
   */
  object Google {
    const val dagger = "2.30.1"
    const val hilt = "${google.dagger}-alpha"
    const val material = "1.3.0-alpha04"
    const val truth = "1.0.1"
  }

  val google = Google

  /**
   * Contains version info for Jake's repositories.
   */
  object Jake {
    const val byteUnits = "0.9.1"
    const val converter = "0.8.0"
    const val timber = "5.0.0-SNAPSHOT"
  }

  val jake = Jake

  const val junit = "4.13"
  const val junit5 = "5.7.0-M1"

  /**
   * Contains version info for kotlin.
   */
  object Kotlin {
    const val runtime = "1.4.20"
    const val coroutines = "1.4.2"
    const val serialization = "1.0.1"
    const val time = "0.1.0"
  }

  val kotlin = Kotlin

  const val ktlint = "9.4.1"

  /**
   * Contains version info for square.
   */
  object Square {
    const val okhttp = "4.10.0-RC1"
    const val retrofit = "2.9.0"
  }

  val square = Square
}

val versions = Versions
