plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
  id("org.jetbrains.kotlin.plugin.serialization")
  id("dagger.hilt.android.plugin")
}

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

  signingConfigs {

    getByName("debug") {
      storeFile = file("keystore/debug.keystore")
      storePassword = "pixels"
      keyAlias = "pixels"
      keyPassword = "pixels"
    }

    if (file("keystore/upload.keystore").exists()) {
      @Suppress("LocalVariableName") val PIXELS_STORE_PASSWORD: String by project
      @Suppress("LocalVariableName") val PIXELS_KEY_ALIAS: String by project
      @Suppress("LocalVariableName") val PIXELS_KEY_PASSWORD: String by project
      getByName("upload") {
        storeFile = file("keystore/upload.keystore")
        storePassword = PIXELS_STORE_PASSWORD
        keyAlias = PIXELS_KEY_ALIAS
        keyPassword = PIXELS_KEY_PASSWORD
      }
    }
  }

  buildTypes {

    getByName("debug") {
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }

    getByName("release") {
      signingConfig = if (file("keystore/upload.keystore").exists()) {
        signingConfigs.getByName("upload")
      } else {
        signingConfigs.getByName("debug")
      }
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + listOf(
      "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
      "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    )
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerVersion = versions.kotlin.runtime
    kotlinCompilerExtensionVersion = versions.androidX.compose.core
  }

  lintOptions {
    disable.add("InvalidFragmentVersionForActivityResult")
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

  implementation(project(":theme"))
  implementation(project(":timber"))

  implementation(deps.kotlin.stdLib.jdk)
  implementation(deps.kotlin.serialization.core)

  implementation(deps.androidX.core)
  implementation(deps.androidX.appcompat)
  implementation(deps.google.material)

  implementation(deps.androidX.compose.compiler)

  implementation(deps.androidX.compose.animation.animation)
  implementation(deps.androidX.compose.animation.core)

  implementation(deps.androidX.compose.foundation.foundation)
  implementation(deps.androidX.compose.foundation.layout)

  implementation(deps.androidX.compose.material.material)
  implementation(deps.androidX.compose.material.icons.core)
  implementation(deps.androidX.compose.material.icons.extended)

  implementation(deps.androidX.compose.runtime.runtime)
  implementation(deps.androidX.compose.runtime.dispatch)
  implementation(deps.androidX.compose.runtime.livedata)
  implementation(deps.androidX.compose.runtime.savedInstanceState)

  implementation(deps.androidX.compose.ui.ui)
  implementation(deps.androidX.compose.ui.geometry)
  implementation(deps.androidX.compose.ui.graphics)
  implementation(deps.androidX.compose.ui.text)
  implementation(deps.androidX.compose.ui.tooling)
  implementation(deps.androidX.compose.ui.unit)
  implementation(deps.androidX.compose.ui.util)
  implementation(deps.androidX.compose.ui.viewbinding)

  implementation(deps.androidX.navigation.compose)

  implementation(deps.chris.coil)

  implementation(deps.jake.timber.android)

  implementation(deps.google.hilt.runtime)
  kapt(deps.google.hilt.compiler)

  implementation(deps.androidX.hilt.common)
  implementation(deps.androidX.hilt.viewModel)
  kapt(deps.androidX.hilt.compiler)

  implementation(deps.square.okhttp.client)
  implementation(deps.square.okhttp.logging)

  implementation(deps.square.retrofit.client)
  implementation(deps.jake.converter)

  implementation(deps.jake.byteunits)

  implementation(deps.androidX.lifecycle.liveData)
  implementation(deps.androidX.lifecycle.runtime)
  implementation(deps.androidX.lifecycle.viewmodel)

  androidTestImplementation(deps.androidX.compose.ui.test.core)
  androidTestImplementation(deps.androidX.compose.ui.test.junit)

  androidTestImplementation(deps.androidX.test.espresso.core)
  androidTestImplementation(deps.androidX.test.junit)
  androidTestImplementation(deps.androidX.test.rules)
  androidTestImplementation(deps.androidX.test.runner)

  androidTestUtil(deps.androidX.test.orchestrator)
}
