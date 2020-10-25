rootProject.name = "Pixels"

listOf(
  ":app",
  ":timber"
).forEach { include(it) }

enableFeaturePreview("GRADLE_METADATA")
