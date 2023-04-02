dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Diagnal Movies"
include(":app")
include(":domain")
include(":data")
include(":core")
include(":home")
