import com.android.build.gradle.internal.plugins.BasePlugin
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
    gradlePluginPortal()
  }

  dependencies {
    classpath(androidConfig.classPath.android)
    classpath(androidConfig.classPath.ben)
    classpath(androidConfig.classPath.hilt)
    classpath(androidConfig.classPath.kotlin.gradle)
    classpath(androidConfig.classPath.kotlin.serialization)
    classpath(androidConfig.classPath.detekt)
    classpath(androidConfig.classPath.ktlint)
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
      }
    }
  }

  apply(plugin = "org.jlleitschuh.gradle.ktlint")
  apply(plugin = "io.gitlab.arturbosch.detekt")

  afterEvaluate {
    tasks.findByName("check")?.dependsOn("detekt")
  }

  plugins.withType<BasePlugin<*, *, *>>().configureEach {
    extension.compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }
  }

  tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
  }

  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
      // Allow warnings when running from IDE, makes it easier to experiment.
      allWarningsAsErrors = true

      freeCompilerArgs = freeCompilerArgs + listOf(
        "-XXLanguage:+NewInference",
        "-progressive",
        "-Xopt-in=kotlin.Experimental",
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlin.time.ExperimentalTime",
        "-Xallow-jvm-ir-dependencies"
      )

      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }

  tasks.withType<Test> {
    testLogging {
      events = setOf(TestLogEvent.SKIPPED, TestLogEvent.FAILED, TestLogEvent.PASSED)
    }
  }

  // Configuration documentation: https://github.com/JLLeitschuh/ktlint-gradle#configuration
  configure<KtlintExtension> {
    version.set("0.38.1")
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
  }

  configure<DetektExtension> {
    config = files("${rootDir}/detekt.yml")
    // Treat config file as an override for the default config.
    buildUponDefaultConfig = true
  }
}
