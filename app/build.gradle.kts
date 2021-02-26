plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
  id("com.squareup.anvil")
  id("com.squareup.sqldelight")
}

kotlin {
  sourceSets {
    all {
      languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
      languageSettings.useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
      languageSettings.useExperimentalAnnotation("kotlinx.serialization.ExperimentalSerializationApi")
      languageSettings.useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
    }
  }
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
    jvmTarget = "1.8"
    useIR = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = versions.androidX.compose.core
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

  implementation(project(":theme:public"))
  implementation(project(":timber:impl-wiring"))
  implementation(project(":database:session:impl-wiring"))
  implementation(project(":scopes"))

  implementation(deps.kotlin.stdLib.jdk)

  implementation(deps.androidX.activity)
  implementation(deps.androidX.appcompat)
  implementation(deps.androidX.core)

  implementation(deps.androidX.compose.activity)

  implementation(deps.androidX.compose.compiler)

  implementation(deps.androidX.compose.animation.animation)
  implementation(deps.androidX.compose.animation.core)

  implementation(deps.androidX.compose.foundation.foundation)
  implementation(deps.androidX.compose.foundation.layout)

  implementation(deps.androidX.compose.material.material)
  implementation(deps.androidX.compose.material.icons.core)
  implementation(deps.androidX.compose.material.icons.extended)

  implementation(deps.androidX.compose.runtime.runtime)
  implementation(deps.androidX.compose.runtime.livedata)
  implementation(deps.androidX.compose.runtime.saveable)

  implementation(deps.androidX.compose.ui.ui)
  implementation(deps.androidX.compose.ui.geometry)
  implementation(deps.androidX.compose.ui.graphics)
  implementation(deps.androidX.compose.ui.text)
  implementation(deps.androidX.compose.ui.tooling)
  implementation(deps.androidX.compose.ui.unit)
  implementation(deps.androidX.compose.ui.util)
  implementation(deps.androidX.compose.ui.viewbinding)

  implementation(deps.androidX.lifecycle.liveData)
  implementation(deps.androidX.lifecycle.runtime)
  implementation(deps.androidX.lifecycle.viewmodel)

  implementation(deps.androidX.navigation.compose)

  implementation(deps.chris.coil)

  implementation(deps.google.dagger.runtime)
  kapt(deps.google.dagger.compiler)

  implementation(deps.google.material)

  implementation(deps.square.okhttp.client)
  implementation(deps.square.okhttp.logging)

  implementation(deps.square.sqlDelight.android)

  implementation(deps.square.retrofit.client)

  implementation(deps.jake.converter)
  implementation(deps.jake.byteunits)

  androidTestImplementation(deps.androidX.compose.ui.test.core)
  androidTestImplementation(deps.androidX.compose.ui.test.junit)

  androidTestImplementation(deps.androidX.test.espresso.core)
  androidTestImplementation(deps.androidX.test.junit)
  androidTestImplementation(deps.androidX.test.rules)
  androidTestImplementation(deps.androidX.test.runner)

  androidTestUtil(deps.androidX.test.orchestrator)
}

sqldelight {
  database("PixelsDb") {
    packageName = "com.adjectivemonk2.pixels.database"
    sourceFolders = listOf("database")
    schemaOutputDirectory = file("build/database")
    dependency(project(":database:session:impl"))
  }
}

kapt {
  correctErrorTypes = true
}
