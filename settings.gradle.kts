rootProject.name = "Pixels"

listOf(
  ":app"
).forEach { include(it) }

enableFeaturePreview("GRADLE_METADATA")
