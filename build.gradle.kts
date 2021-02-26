import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
  id("com.github.ben-manes.versions") version versions.ben
}

buildscript {

  repositories {
    googleRepositories()
    kotlinEAPRepositories()
    mavenCentralRepositories()
    kotlinXRepositories()
    springPluginsRepositories()
    snapShotRepositories()
    gradlePluginPortal()
  }

  dependencies {
    classpath(androidConfig.classPath.android)
    classpath(androidConfig.classPath.ben)
    classpath(androidConfig.classPath.kotlin.gradle)
    classpath(androidConfig.classPath.kotlin.serialization)
    classpath(androidConfig.classPath.detekt)
    classpath(androidConfig.classPath.ktlint)
    classpath(androidConfig.classPath.square.anvil)
    classpath(androidConfig.classPath.square.sqlDelight)
  }
}

subprojects {
  repositories {
    googleRepositories()
    kotlinEAPRepositories()
    mavenCentralRepositories()
    kotlinXRepositories()
    springPluginsRepositories()
    snapShotRepositories()
    gradlePluginPortal()
  }

  configurations.all {
    resolutionStrategy {
      eachDependency {
        // Force all Kotlin stdlib artifacts to use the same version.
        if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin-stdlib")) {
          useVersion(versions.kotlin.runtime)
        }
        if (requested.group == "androidx.test.ext" && requested.name.startsWith("junit-ktx")) {
          useVersion(versions.androidX.test.junit)
        }
      }
    }
  }

  apply(plugin = "org.jlleitschuh.gradle.ktlint")
  apply(plugin = "io.gitlab.arturbosch.detekt")

  afterEvaluate {
    tasks.findByName("check")?.dependsOn("detekt")
  }

  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      allWarningsAsErrors = true

      freeCompilerArgs = freeCompilerArgs + listOf(
        "-XXLanguage:+NewInference",
        "-progressive"
      )

      jvmTarget = "1.8"
    }
  }

  tasks.withType<Test> {
    testLogging {
      events = setOf(TestLogEvent.SKIPPED, TestLogEvent.FAILED, TestLogEvent.PASSED)
    }
  }

  // Configuration documentation: https://github.com/JLLeitschuh/ktlint-gradle#configuration
  configure<KtlintExtension> {
    version.set("0.40.0")
    // Prints the name of failed rules.
    verbose.set(true)
    reporters {
      // Default "plain" reporter is actually harder to read.
      reporter(ReporterType.JSON)
    }

    disabledRules.set(
      setOf(
        // IntelliJ refuses to sort imports correctly.
        // This is a known issue: https://github.com/pinterest/ktlint/issues/527
        "import-ordering"
      )
    )

    filter {
      exclude { element -> element.file.path.contains("generated/") }
      exclude("**/generated/**")
      include("**/kotlin/**")
    }
  }

  configure<DetektExtension> {
    config = files("${rootDir}/detekt.yml")
    // Treat config file as an override for the default config.
    buildUponDefaultConfig = true
  }

  // FIXME: Check https://github.com/gradle/gradle/issues/4823
  parent!!.path.takeIf { it != rootProject.path }?.let { evaluationDependsOn(it) }
}
