@file:Suppress("SpellCheckingInspection", "unused")

/**
 * Has all the dependencies that we are going to use in the app.
 */
object Dependencies {

  /**
   * Has dependencies that are part of the androidx.
   */
  object AndroidX {
    const val activity = "androidx.activity:activity-ktx:${versions.androidX.activity}"
    const val appcompat = "androidx.appcompat:appcompat:${versions.androidX.appcompat}"
    const val core = "androidx.core:core-ktx:${versions.androidX.core}"

    /**
     * Has dependencies that are part of compose.
     */
    object Compose {
      const val compiler = "androidx.compose:compose-compiler:${versions.androidX.compose}"

      object Animation {
        const val animation = "androidx.compose.animation:animation:${versions.androidX.compose}"
        const val core = "androidx.compose.animation:animation-core:${versions.androidX.compose}"
      }

      val animation = Animation

      object Foundation {
        const val foundation = "androidx.compose.foundation:foundation:${versions.androidX.compose}"
        const val layout =
          "androidx.compose.foundation:foundation-layout:${versions.androidX.compose}"
        const val text = "androidx.compose.foundation:foundation-text:${versions.androidX.compose}"
      }

      val foundation = Foundation

      object Material {
        const val material = "androidx.compose.material:material:${versions.androidX.compose}"

        object Icons {
          const val core =
            "androidx.compose.material:material-icons-core:${versions.androidX.compose}"
          const val extended =
            "androidx.compose.material:material-icons-extended:${versions.androidX.compose}"
        }

        val icons = Icons
      }

      val material = Material

      object Runtime {
        const val runtime = "androidx.compose.runtime:runtime:${versions.androidX.compose}"
        const val dispatch =
          "androidx.compose.runtime:runtime-dispatch:${versions.androidX.compose}"
        const val livedata =
          "androidx.compose.runtime:runtime-livedata:${versions.androidX.compose}"
        const val savedInstanceState =
          "androidx.compose.runtime:runtime-saved-instance-state:${versions.androidX.compose}"
      }

      val runtime = Runtime

      object Ui {
        const val ui = "androidx.compose.ui:ui:${versions.androidX.compose}"
        const val geometry = "androidx.compose.ui:ui-geometry:${versions.androidX.compose}"
        const val graphics = "androidx.compose.ui:ui-graphics:${versions.androidX.compose}"
        const val text = "androidx.compose.ui:ui-text:${versions.androidX.compose}"
        const val textAndroid = "androidx.compose.ui:ui-text-android:${versions.androidX.compose}"
        const val unit = "androidx.compose.ui:ui-unit:${versions.androidX.compose}"
        const val util = "androidx.compose.ui:ui-util:${versions.androidX.compose}"
        const val viewbinding = "androidx.compose.ui:ui-viewbinding:${versions.androidX.compose}"
      }

      val ui = Ui
    }

    val compose = Compose

    /**
     * Has dependencies that are part of the androidx and fragments.
     */
    object Fragment {
      const val ktx = "androidx.fragment:fragment-ktx:${versions.androidX.fragment}"
      const val test = "androidx.fragment:fragment-testing:${versions.androidX.fragment}"
    }

    val fragment = Fragment

    object Hilt {
      const val common = "androidx.hilt:hilt-common:${versions.androidX.hilt}"
      const val compiler = "androidx.hilt:hilt-compiler:${versions.androidX.hilt}"
      const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${versions.androidX.hilt}"
      const val work = "androidx.hilt:hilt-work:${versions.androidX.hilt}"
    }

    val hilt = Hilt

    /**
     * Has dependencies that are part of the androidx and lifecycle.
     */
    object Lifecycle {
      const val java8 = "androidx.lifecycle:lifecycle-common-java8:${versions.androidX.lifecycle}"
      const val liveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${versions.androidX.lifecycle}"
      const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${versions.androidX.lifecycle}"
      const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${versions.androidX.lifecycle}"
    }

    val lifecycle = Lifecycle

    /**
     * Has dependencies that are part of the androidx and navigation.
     */
    object Navigation {
      const val fragment =
        "androidx.navigation:navigation-fragment-ktx:${versions.androidX.navigation}"
      const val ui = "androidx.navigation:navigation-ui-ktx:${versions.androidX.navigation}"
    }

    val navigation = Navigation

    /**
     * Has dependencies that are part of androidx and room
     */
    object Room {
      const val runtime = "androidx.room:room-ktx:${versions.androidX.room}"
      const val compiler = "androidx.room:room-compiler:${versions.androidX.room}"
      const val test = "androidx.room:room-testing:${versions.androidX.room}"
    }

    val room = Room

    /**
     * Has dependencies that are part of the androidx and test.
     */
    object Test {

      /**
       * Has dependencies that are part of the androidx and expresso.
       */
      object Espresso {
        const val core = "androidx.test.espresso:espresso-core:${versions.androidX.test.espresso}"
      }

      val espresso = Espresso

      const val junit = "androidx.test.ext:junit:${versions.androidX.test.junit}"
      const val orchestrator = "androidx.test:orchestrator:${versions.androidX.test.orchestrator}"
      const val rules = "androidx.test:rules:${versions.androidX.test.rules}"
      const val runner = "androidx.test:runner:${versions.androidX.test.runner}"
    }

    val test = Test

    object Ui {
      const val tooling = "androidx.ui:ui-tooling:${versions.androidX.compose}"
    }

    val ui = Ui
  }

  val androidX = AndroidX

  object Chris {
    const val coil = "dev.chrisbanes.accompanist:accompanist-coil:${versions.chris.coil}"
  }

  val chris = Chris

  /**
   * Has dependencies that are part of the google.
   */
  object Google {

    /**
     * Has dependencies that are part of the google and dagger.
     */
    object Dagger {
      const val compiler = "com.google.dagger:dagger-compiler:${versions.google.dagger}"
      const val runtime = "com.google.dagger:dagger:${versions.google.dagger}"
    }

    val dagger = Dagger

    object Hilt {
      const val compiler = "com.google.dagger:hilt-android-compiler:${versions.google.hilt}"
      const val runtime = "com.google.dagger:hilt-android:${versions.google.hilt}"
      const val test = "com.google.dagger:hilt-android-testing:${versions.google.hilt}"
    }

    val hilt = Hilt

    const val material = "com.google.android.material:material:${versions.google.material}"
    const val truth = "com.google.truth:truth:${versions.google.truth}"
  }

  val google = Google

  /**
   * Has dependencies that are part of the jake.
   */
  object Jake {
    const val converter =
      "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${versions.jake.converter}"
    const val byteunits = "com.jakewharton.byteunits:byteunits:${versions.jake.byteUnits}"

    /**
     * Has dependencies that are part of the jake and timber.
     */
    object Timber {
      const val android = "com.jakewharton.timber:timber-android:${versions.jake.timber}"
      const val common = "com.jakewharton.timber:timber-common:${versions.jake.timber}"
      const val jdk = "com.jakewharton.timber:timber-jdk:${versions.jake.timber}"
    }

    val timber = Timber
  }

  val jake = Jake

  /**
   * Has dependencies that are part of junit.
   */
  object JUnit {
    const val jupiter = "org.junit.jupiter:junit-jupiter:${versions.junit5}"
    const val legacy = "junit:junit:${versions.junit}"

    /**
     * The engine that is used to run juni5
     */
    object Engine {
      const val jupiter = "org.junit.jupiter:junit-jupiter-engine:${versions.junit5}"
    }

    val engine = Engine
  }

  val junit = JUnit

  /**
   * Has dependencies that are part of the kotlin.
   */
  object Kotlin {

    /**
     * Has dependencies that are part of the kotlin and coroutines.
     */
    object Coroutines {
      const val android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlin.coroutines}"
      const val common =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions.kotlin.coroutines}"
      const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${versions.kotlin.coroutines}"
      const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.kotlin.coroutines}"
    }

    val coroutines = Coroutines

    /**
     * Has dependencies that are part of the kotlin and serialization.
     */
    object Serialization {
      const val core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${versions.kotlin.serialization}"
    }

    val serialization = Serialization

    /**
     * Has dependencies that are part of the kotlin and stdlib.
     */
    object StdLib {
      const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${versions.kotlin.runtime}"
      const val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin.runtime}"
    }

    val stdLib = StdLib

    /**
     * Has dependencies that are part of the kotlin and test.
     */
    object Test {
      const val annotations =
        "org.jetbrains.kotlin:kotlin-test-annotations-common:${versions.kotlin.runtime}"
      const val common = "org.jetbrains.kotlin:kotlin-test-common:${versions.kotlin.runtime}"
      const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin.runtime}"
    }

    val test = Test
  }

  val kotlin = Kotlin

  /**
   * Has dependencies that are part of the Square.
   */
  object Square {

    /**
     * Has dependencies that are part of the Square and okhttp.
     */
    object Okhttp {
      const val client = "com.squareup.okhttp3:okhttp:${versions.square.okhttp}"
      const val logging = "com.squareup.okhttp3:logging-interceptor:${versions.square.okhttp}"
      const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${versions.square.okhttp}"
    }

    val okhttp = Okhttp

    /**
     * Has dependencies that are part of the Square and retrofit.
     */
    object Retrofit {
      const val client = "com.squareup.retrofit2:retrofit:${versions.square.retrofit}"
    }

    val retrofit = Retrofit
  }

  val square = Square
}

val deps = Dependencies
