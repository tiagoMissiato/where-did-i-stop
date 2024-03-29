pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Where did I stop"

include(":app")
include(":core-ui")
include(":core-network")
include(":core-database")
include(":movie")
include(":ui-component")
include(":core-data")
include(":core-domain")
include(":core-model")
include(":tvshow")
include(":core-testing")
