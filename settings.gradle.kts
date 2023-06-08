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
include(":affirmations")//
include(":woof-App")
include(":DessertClicker")
include(":UnscrambleGame")
include(":CupCakeNavigation")
include(":LunchTrayNavigationPractice")
include(":Reply-Adaptive-app-with-dynamic-navigation")
include(":SportApp-LargeScreenAdaptation")
include(":RaceTrackerCoroutines")
include(":MarsPhotos-Internet")
include(":SQLBasics")
include(":InventoryApp-RoomDb")
