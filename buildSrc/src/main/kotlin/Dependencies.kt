@file:Suppress("SpellCheckingInspection", "unused")
@file:JvmName("Deps")

import java.util.Locale.US
import kotlin.reflect.full.declaredMembers

object Dependencies {

  object AndroidX {
    const val activity = "androidx.activity:activity-ktx:${versions.androidX.activity}"
    const val appcompat = "androidx.appcompat:appcompat:${versions.androidX.appcompat}"
    const val core = "androidx.core:core-ktx:${versions.androidX.core}"

    object Compose {
      const val activity = "androidx.activity:activity-compose:${versions.androidX.activity}"
      const val compiler = "androidx.compose.compiler:compiler:${versions.androidX.compose.core}"

      object Animation {
        const val animation =
          "androidx.compose.animation:animation:${versions.androidX.compose.core}"
        const val core =
          "androidx.compose.animation:animation-core:${versions.androidX.compose.core}"
      }

      val animation = Animation

      object Foundation {
        const val foundation =
          "androidx.compose.foundation:foundation:${versions.androidX.compose.core}"
        const val layout =
          "androidx.compose.foundation:foundation-layout:${versions.androidX.compose.core}"
      }

      val foundation = Foundation

      object Material {
        const val material = "androidx.compose.material:material:${versions.androidX.compose.core}"

        object Icons {
          const val core =
            "androidx.compose.material:material-icons-core:${versions.androidX.compose.core}"
          const val extended =
            "androidx.compose.material:material-icons-extended:${versions.androidX.compose.core}"
        }

        val icons = Icons
      }

      val material = Material

      object Runtime {
        const val runtime = "androidx.compose.runtime:runtime:${versions.androidX.compose.core}"
        const val livedata =
          "androidx.compose.runtime:runtime-livedata:${versions.androidX.compose.core}"
        const val saveable =
          "androidx.compose.runtime:runtime-saveable:${versions.androidX.compose.core}"
      }

      val runtime = Runtime

      object Ui {
        const val ui = "androidx.compose.ui:ui:${versions.androidX.compose.core}"
        const val geometry = "androidx.compose.ui:ui-geometry:${versions.androidX.compose.core}"
        const val graphics = "androidx.compose.ui:ui-graphics:${versions.androidX.compose.core}"
        const val text = "androidx.compose.ui:ui-text:${versions.androidX.compose.core}"
        const val tooling = "androidx.compose.ui:ui-tooling:${versions.androidX.compose.core}"
        const val unit = "androidx.compose.ui:ui-unit:${versions.androidX.compose.core}"
        const val util = "androidx.compose.ui:ui-util:${versions.androidX.compose.core}"
        const val viewbinding =
          "androidx.compose.ui:ui-viewbinding:${versions.androidX.compose.core}"

        object Test {
          const val core = "androidx.compose.ui:ui-test:${versions.androidX.compose.core}"
          const val junit = "androidx.compose.ui:ui-test-junit4:${versions.androidX.compose.core}"
        }

        val test = Test
      }

      val ui = Ui
    }

    val compose = Compose

    object Fragment {
      const val ktx = "androidx.fragment:fragment-ktx:${versions.androidX.fragment}"
      const val test = "androidx.fragment:fragment-testing:${versions.androidX.fragment}"
    }

    val fragment = Fragment

    object Lifecycle {
      const val java8 = "androidx.lifecycle:lifecycle-common-java8:${versions.androidX.lifecycle}"
      const val liveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${versions.androidX.lifecycle}"
      const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${versions.androidX.lifecycle}"
      const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${versions.androidX.lifecycle}"
      const val compose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${versions.androidX.compose.lifecycle}"
    }

    val lifecycle = Lifecycle

    object Navigation {
      const val compose =
        "androidx.navigation:navigation-compose:${versions.androidX.compose.navigation}"
    }

    val navigation = Navigation

    object Paging {
      const val compose = "androidx.paging:paging-compose:${versions.androidX.compose.paging}"
    }

    val paging = Paging

    object Test {

      object Espresso {
        const val core = "androidx.test.espresso:espresso-core:${versions.androidX.test.espresso}"
      }

      val espresso = Espresso

      const val junit = "androidx.test.ext:junit-ktx:${versions.androidX.test.junit}"
      const val orchestrator = "androidx.test:orchestrator:${versions.androidX.test.orchestrator}"
      const val rules = "androidx.test:rules:${versions.androidX.test.rules}"
      const val runner = "androidx.test:runner:${versions.androidX.test.runner}"
      const val truth = "androidx.test.ext:truth:${versions.androidX.test.truth}"
    }

    val test = Test
  }

  val androidX = AndroidX

  object Chris {
    const val coil = "dev.chrisbanes.accompanist:accompanist-coil:${versions.chris.coil}"
  }

  val chris = Chris

  object Google {

    object Dagger {
      const val compiler = "com.google.dagger:dagger-compiler:${versions.google.dagger}"
      const val runtime = "com.google.dagger:dagger:${versions.google.dagger}"
    }

    val dagger = Dagger

    const val material = "com.google.android.material:material:${versions.google.material}"
    const val truth = "com.google.truth:truth:${versions.google.truth}"
  }

  val google = Google

  object Jake {
    const val converter =
      "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${versions.jake.converter}"
    const val byteunits = "com.jakewharton.byteunits:byteunits:${versions.jake.byteUnits}"

    object Timber {
      const val android = "com.jakewharton.timber:timber-android:${versions.jake.timber}"
      const val common = "com.jakewharton.timber:timber-common:${versions.jake.timber}"
      const val jdk = "com.jakewharton.timber:timber-jdk:${versions.jake.timber}"
    }

    val timber = Timber
  }

  val jake = Jake

  object JUnit {

    object Jupiter {
      const val engine = "org.junit.jupiter:junit-jupiter-engine:${versions.junit5}"
      const val runtime = "org.junit.jupiter:junit-jupiter:${versions.junit5}"
    }

    val jupiter = Jupiter

    object Legacy {
      const val runtime = "junit:junit:${versions.junit}"
      const val engine = "org.junit.vintage:junit-vintage-engine:${versions.junit5}"
    }

    val legacy = Legacy
  }

  val junit = JUnit

  object Kotlin {

    object Coroutines {
      const val android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlin.coroutines}"
      const val common =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlin.coroutines}"
      const val jdk = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${versions.kotlin.coroutines}"
      const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.kotlin.coroutines}"
    }

    val coroutines = Coroutines

    object Serialization {
      const val core =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${versions.kotlin.serialization}"
    }

    val serialization = Serialization

    object StdLib {
      const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${versions.kotlin.runtime}"
      const val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin.runtime}"
    }

    val stdLib = StdLib

    object Test {
      const val annotations =
        "org.jetbrains.kotlin:kotlin-test-annotations-common:${versions.kotlin.runtime}"
      const val common = "org.jetbrains.kotlin:kotlin-test-common:${versions.kotlin.runtime}"
      const val junit = "org.jetbrains.kotlin:kotlin-test-junit5:${versions.kotlin.runtime}"
    }

    val test = Test

    const val time = "org.jetbrains.kotlinx:kotlinx-datetime:${versions.kotlin.time}"
  }

  val kotlin = Kotlin

  object Square {

    object Okhttp {
      const val client = "com.squareup.okhttp3:okhttp:${versions.square.okhttp}"
      const val logging = "com.squareup.okhttp3:logging-interceptor:${versions.square.okhttp}"
      const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${versions.square.okhttp}"
    }

    val okhttp = Okhttp

    object Retrofit {
      const val client = "com.squareup.retrofit2:retrofit:${versions.square.retrofit}"
    }

    val retrofit = Retrofit

    object SqlDelight {
      const val android = "com.squareup.sqldelight:android-driver:${versions.square.sqlDelight}"
      const val coroutines =
        "com.squareup.sqldelight:coroutines-extensions:${versions.square.sqlDelight}"
      const val jvm = "com.squareup.sqldelight:sqlite-driver:${versions.square.sqlDelight}"
      const val runtime = "com.squareup.sqldelight:runtime:${versions.square.sqlDelight}"
    }

    val sqlDelight = SqlDelight
  }

  val square = Square
}

val deps = Dependencies

/**
 * Workaround to make [Dependencies] accessible from Groovy scripts. [path] is case-insensitive.
 *
 * ```
 * dependencies {
 *   implementation Deps.get("kotlin.stdlib.common")
 * }
 * ```
 */
@JvmName("get")
fun getDependencyFromGroovy(path: String): String = try {
  Dependencies.resolveObject(path.toLowerCase(US).split("."))
} catch (e: Throwable) {
  throw IllegalArgumentException("Error resolving dependency: $path", e)
}

private tailrec fun Any.resolveObject(pathParts: List<String>): String {
  require(pathParts.isNotEmpty())
  val klass = this::class

  if (pathParts.size == 1) {
    @Suppress("UNCHECKED_CAST")
    val member = klass.declaredMembers.single { it.name.toLowerCase(US) == pathParts.single() }
    return member.call() as String
  }

  val nestedKlasses = klass.nestedClasses
  val selectedKlass = nestedKlasses.single { it.simpleName!!.toLowerCase(US) == pathParts.first() }
  return selectedKlass.objectInstance!!.resolveObject(pathParts.subList(1, pathParts.size))
}
