rootProject.name = "Pixels"

listOf(
  ":app",
  ":theme",
  ":timber"
).forEach { include(it) }

enableFeaturePreview("GRADLE_METADATA")
