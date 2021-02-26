plugins {
  id("org.jetbrains.kotlin.jvm")
}

base.archivesBaseName = "scopes"

kotlin {

  explicitApi()

  target {
    compilations.getting {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }
}

dependencies {
  implementation(deps.google.dagger.runtime)
}
