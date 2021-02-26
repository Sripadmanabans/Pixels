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
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = versions.androidX.compose.core
  }

  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + listOf(
      "-Xexplicit-api=strict",
      "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    )
    jvmTarget = "1.8"
    useIR = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  packagingOptions {
    resources {
      excludes.apply {
        add("META-INF/AL2.0")
        add("META-INF/LGPL2.1")
      }
    }
  }

  testOptions {
    animationsDisabled = true
    execution = "ANDROIDX_TEST_ORCHESTRATOR"
  }
}

dependencies {
  implementation(deps.androidX.compose.foundation.foundation)

  implementation(deps.androidX.compose.material.material)

  implementation(deps.androidX.compose.runtime.runtime)

  implementation(deps.androidX.compose.ui.text)
  implementation(deps.androidX.compose.ui.ui)
  implementation(deps.androidX.compose.ui.unit)

  androidTestImplementation(deps.androidX.compose.ui.test.core)
  androidTestImplementation(deps.androidX.compose.ui.test.junit)

  androidTestImplementation(deps.androidX.test.espresso.core)
  androidTestImplementation(deps.androidX.test.junit)
  androidTestImplementation(deps.androidX.test.rules)
  androidTestImplementation(deps.androidX.test.runner)

  androidTestUtil(deps.androidX.test.orchestrator)
}
