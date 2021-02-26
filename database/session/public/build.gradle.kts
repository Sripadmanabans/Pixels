plugins {
  id("org.jetbrains.kotlin.jvm")
}

base.archivesBaseName = "database-session-public"

kotlin {

  explicitApi()

  target {
    compilations.getting {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }

  sourceSets {
    all {
      languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
    }
  }
}

dependencies {
  implementation(deps.kotlin.coroutines.common)
  implementation(deps.kotlin.time)
}
