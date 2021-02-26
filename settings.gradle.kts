rootProject.name = "Pixels"

listOf(
  ":app",
  ":database:session:impl",
  ":database:session:impl-wiring",
  ":database:session:public",
  ":scopes",
  ":theme:public",
  ":timber:impl",
  ":timber:impl-wiring",
  ":timber:public"
).forEach { include(it) }

enableFeaturePreview("GRADLE_METADATA")
