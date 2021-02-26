object Versions {

  object AndroidX {
    const val activity = "1.3.0-alpha03"
    const val appcompat = "1.3.0-beta01"
    const val core = "1.5.0-beta02"
    const val fragment = "1.3.0-alpha06"
    const val lifecycle = "2.3.0"

    object Compose {
      const val core = "1.0.0-beta01"
      const val lifecycle = "1.0.0-alpha01"
      const val navigation = "1.0.0-alpha08"
      const val paging = "1.0.0-alpha07"
    }

    val compose = Compose

    object Test {
      const val espresso = "3.4.0-alpha04"
      const val junit = "1.1.3-alpha04"
      const val orchestrator = "1.3.1-alpha03"
      const val rules = "1.4.0-alpha04"
      const val runner = "1.4.0-alpha04"
      const val truth = "1.4.0-alpha04"
    }

    val test = Test
  }

  val androidX = AndroidX

  const val ben = "0.36.0"

  object Chris {
    const val coil = "0.6.0"
  }

  val chris = Chris

  const val detekt = "1.16.0-RC2"

  object Google {
    const val dagger = "2.32"
    const val material = "1.4.0-alpha01"
    const val truth = "1.1"
  }

  val google = Google

  object Jake {
    const val byteUnits = "0.9.1"
    const val converter = "0.8.0"
    const val timber = "5.0.0-SNAPSHOT"
  }

  val jake = Jake

  const val junit = "4.13"
  const val junit5 = "5.7.0"

  object Kotlin {
    const val runtime = "1.4.30"
    const val coroutines = "1.4.2"
    const val serialization = "1.0.1"
    const val time = "0.1.1"
  }

  val kotlin = Kotlin

  const val ktlint = "10.0.0"

  object Square {
    const val anvil = "2.1.0"
    const val okhttp = "5.0.0-alpha.2"
    const val retrofit = "2.9.0"
    const val sqlDelight = "1.5.0-SNAPSHOT"
  }

  val square = Square
}

val versions = Versions
