plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
}

base.archivesBaseName = "theme"

android {
  compileSdkVersion(androidConfig.compileSdk)

  defaultConfig {
    minSdkVersion(androidConfig.minSdk)
    targetSdkVersion(androidConfig.targetSdk)
    versionCode = androidConfig.version.code
    versionName = androidConfig.version.fullName
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }
  }

  buildFeatures {
    buildConfig = false
    compose = true
  }

  composeOptions {
    kotlinCompilerVersion = versions.kotlin.runtime
    kotlinCompilerExtensionVersion = versions.androidX.compose.core
  }

  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=strict"
  }
}

dependencies {
  implementation(deps.androidX.compose.foundation.foundation)

  implementation(deps.androidX.compose.material.material)

  implementation(deps.androidX.compose.runtime.runtime)

  implementation(deps.androidX.compose.ui.text)
  implementation(deps.androidX.compose.ui.ui)
  implementation(deps.androidX.compose.ui.unit)
}
