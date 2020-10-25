plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
}

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
  }
}

dependencies {
  implementation(deps.jake.timber.android)

  implementation(deps.google.dagger.runtime)
  kapt(deps.google.dagger.compiler)
}
