plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.squareup.sqldelight")
  id("com.squareup.anvil")
}

base.archivesBaseName = "database-session-impl"

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
      languageSettings.useExperimentalAnnotation("kotlinx.coroutines.FlowPreview")
    }
  }
}

dependencies {
  api(project(":database:session:public"))

  implementation(project(":scopes"))

  implementation(deps.google.dagger.runtime)

  implementation(deps.kotlin.coroutines.common)
  implementation(deps.kotlin.time)

  implementation(deps.square.sqlDelight.runtime)
  implementation(deps.square.sqlDelight.coroutines)
}

sqldelight {
  database("PixelsDb") {
    packageName = "com.adjectivemonk2.pixels.database.session"
    sourceFolders = listOf("database")
    schemaOutputDirectory = file(" build/database")
  }
}

anvil {
  generateDaggerFactories = true
}
