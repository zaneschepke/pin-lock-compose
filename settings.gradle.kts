pluginManagement {
    repositories {
        maven("https://gitea.zaneschepke.com/api/packages/zane/maven")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://gitea.zaneschepke.com/api/packages/zane/maven")
        google()
        mavenCentral()
    }
}
rootProject.name = "PinLockComposeDemo"
include(":app")
include(":pin_lock_compose")
