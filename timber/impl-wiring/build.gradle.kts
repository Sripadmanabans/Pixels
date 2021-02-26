plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("com.squareup.anvil")
}

base.archivesBaseName = "timber-impl-wiring"

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

  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + "-Xexplicit-api=strict"
    jvmTarget = "1.8"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  api(project(":timber:public"))
  api(project(":timber:impl"))

  implementation(project(":scopes"))

  implementation(deps.google.dagger.runtime)
}

anvil {
  generateDaggerFactories = true
}
