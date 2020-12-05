@file:Suppress("unused")

/**
 * Includes information that is very heavily related to android.
 */
object AndroidConfig {

  /**
   * Includes all the classpath's.
   */
  object Classpath {
    const val android = "com.android.tools.build:gradle:7.0.0-alpha02"
    const val ben = "com.github.ben-manes:gradle-versions-plugin:${versions.ben}"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${versions.detekt}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${versions.google.hilt}"

    /**
     * Has classpath's related to kotlin.
     */
    object Kotlin {
      const val gradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin.runtime}"
      const val serialization =
        "org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin.runtime}"
    }

    val kotlin = Kotlin

    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${versions.ktlint}"
  }

  val classPath = Classpath

  const val compileSdk = 30
  const val minSdk = 29
  const val targetSdk = 30

  /**
   * Has versions that are associated with android.
   */
  object Version {
    private const val major = 1
    private const val minor = 0
    private const val patch = 0
    private const val build = 0

    @Suppress("MemberVisibilityCanBePrivate")
    const val name = "${major}.${minor}.${patch}"
    const val fullName = "${name}.${build}"
    const val code = major * 1000000 + minor * 10000 + patch * 100 + build
  }

  val version = Version
}

val androidConfig = AndroidConfig
