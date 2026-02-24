pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BorneoPOS"

include(":app")
include(":core")
include(":domain")
include(":data")
include(":features:checkout")
include(":features:inventory")
include(":features:reports")
include(":features:hardware")
