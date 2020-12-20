plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
}

base.archivesBaseName = "timber"

android {
  compileSdkVersion(androidConfig.compileSdk)

  defaultConfig {
    minSdkVersion(androidConfig.minSdk)
    targetSdkVersion(androidConfig.targetSdk)
    versionCode = androidConfig.version.code
    versionName = androidConfig.version.fullName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    testInstrumentationRunnerArguments["clearPackageData"] = "true"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }
  }

  buildFeatures {
    buildConfig = false
  }

  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=strict"
  }

  testOptions {
    animationsDisabled = true
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
  }
}

dependencies {
  implementation(deps.jake.timber.android)

  implementation(deps.google.dagger.runtime)
  kapt(deps.google.dagger.compiler)

  androidTestImplementation(deps.androidX.test.espresso.core)
  androidTestImplementation(deps.androidX.test.junit)
  androidTestImplementation(deps.androidX.test.truth)
  androidTestImplementation(deps.androidX.test.rules)
  androidTestImplementation(deps.androidX.test.runner)

  androidTestUtil(deps.androidX.test.orchestrator)
}
