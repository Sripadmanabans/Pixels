@file:Suppress("SpellCheckingInspection", "UnstableApiUsage")

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven

/**
 * Has all the info that will be used to add custom repositories.
 */
data class Repositories(
  val url: String,
  val includeGroups: List<String> = listOf(),
  val includeRegexen: List<Regex> = listOf(),
  val excludeGroup: List<String> = listOf(),
  val excludeRegexen: List<Regex> = listOf()
)

/**
 * Used to indicate what repositories are to be fetched from google.
 */
fun RepositoryHandler.googleRepositories() {
  addRepository(
    Repositories(
      url = "http://maven.google.com/",
      includeGroups = listOf(
        "com.google.android.material",
        "com.google.test.platform",
        "com.google.testing.platform",
        "org.jetbrains.kotlin"
      ),
      includeRegexen = listOf(
        "com.android.*".toRegex(),
        "androidx.*".toRegex()
      )
    )
  )
}

/**
 * Used to indicate what repositories are to be fetched from maven central.
 */
fun RepositoryHandler.mavenCentralRepositories() {
  addRepository(
    Repositories(
      url = "https://repo.maven.apache.org/maven2/",
      includeGroups = listOf(
        "it.unimi.dsi",
        "junit",
        "net.ltgt.gradle.incap",
        "org.apiguardian",
        "org.bouncycastle",
        "org.checkerframework",
        "org.glassfish.jaxb",
        "org.hamcrest",
        "org.jvnet.staxex",
        "org.opentest4j",
        "org.ow2.asm",
        "org.sonatype.oss"
      ),
      includeRegexen = listOf(
        "commons-.*".toRegex(),
        "javax.*".toRegex(),
        "net.sf.*".toRegex(),
        "org.apache.*".toRegex(),
        "org.codehaus.*".toRegex(),
        "com.google.*".toRegex(),
        "com.jakewharton.*".toRegex(),
        "org.jetbrains.*".toRegex(),
        "org.junit.*".toRegex(),
        "com.squareup.*".toRegex(),
        "com.sun.*".toRegex()
      ),
      excludeGroup = listOf(
        "com.jakewharton.timber",
        "org.jetbrains.kotlinx"
      )
    )
  )
}

/**
 * Used to indicate what repositories are to be fetched from kotlinx.
 */
fun RepositoryHandler.kotlinEAPRepositories() {
  addRepository(
    Repositories(
      url = "https://dl.bintray.com/kotlin/kotlin-eap/",
      includeGroups = listOf("org.jetbrains.kotlin")
    )
  )
}


/**
 * Used to indicate what repositories are to be fetched from kotlinx.
 */
fun RepositoryHandler.kotlinXRepositories() {
  addRepository(
    Repositories(
      url = "https://kotlin.bintray.com/kotlinx",
      includeGroups = listOf("org.jetbrains.kotlinx")
    )
  )
}

/**
 * Used to indicate what repositories are to be fetched from Sping plugin.
 */
fun RepositoryHandler.springPluginsRepositories() {
  addRepository(
    Repositories(
      url = "https://repo.spring.io/plugins-release/",
      includeGroups = listOf("org.jetbrains.trove4j")
    )
  )
}

/**
 * Used to indicate what repositories are to be fetched from OSS snapshot.
 */
fun RepositoryHandler.snapShotRepositories() {
  addRepository(
    Repositories(
      url = "https://oss.sonatype.org/content/repositories/snapshots/",
      includeGroups = listOf("com.jakewharton.timber")
    )
  )
}

/**
 * Used to indicate what repositories are to be fetched from based on the info provided..
 */
private fun RepositoryHandler.addRepository(repositories: Repositories) {
  maven(url = repositories.url) {
    content {
      repositories.includeGroups.forEach { includeGroup(it) }
      repositories.includeRegexen.forEach { includeGroupByRegex(it.toString()) }

      repositories.excludeGroup.forEach { excludeGroup(it) }
      repositories.excludeRegexen.forEach { excludeGroupByRegex(it.toString()) }
    }
  }
}
