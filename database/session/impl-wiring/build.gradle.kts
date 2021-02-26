plugins {
  id("org.jetbrains.kotlin.jvm")
  id("com.squareup.anvil")
}

base.archivesBaseName = "database-session-impl-wiring"

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
  api(project(":database:session:public"))
  api(project(":database:session:impl"))

  implementation(project(":scopes"))

  implementation(deps.google.dagger.runtime)
}

anvil {
  generateDaggerFactories = true
}
