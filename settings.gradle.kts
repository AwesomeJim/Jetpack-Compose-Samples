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

rootProject.name = "My Compose Application"
include(":app")
include(":BasicLayoutsCodelab")
include(":basicstatecodelab")
include(":themingCodelab")
include(":AnimationCodelab")
include(":NavigationCodelab")
include(":tipcalculator")
include (":affirmations")//
include(":woof-App")
include(":DessertClicker")
include(":UnscrambleGame")
