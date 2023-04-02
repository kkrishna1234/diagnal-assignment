object Libraries {

    // Android
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX_VERSION}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"

    // UI
    const val CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT_VERSION}"
    const val MATERIAL_UI = "com.google.android.material:material:${Versions.MATERIAL_UI_VERSION}"

    // Architecture components
    const val VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_VERSION}"
    const val LIFECYCLE_RUNTIME_KTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_VERSION}"
    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING_LIBRARY_VERSION}"

    // Dependecy injection
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN_VERSION}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versions.KOIN_VERSION}"

    // Converters
    const val GSON_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT_VERSION}"

    // Coroutines
    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_VERSION}"

    // Timber
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER_VERSION}"

    // Firebase
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM_VERSION}"

    // Unit Testing
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT_VERSION}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK_VERSION}"
    const val ARCH_CORE_TESTING =
        "android.arch.core:core-testing:${Versions.ARCH_CORE_TESTING_VERSION}"
    const val COROUTINE_TESTING =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINE_VERSION}"
    const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test-junit:${Kotlin.kotlinVersion}"

    // UI testing
    const val ESPRESSO_CORE =
        "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE_VERSION}"
    const val UI_TEST_RUNNER = "androidx.test:runner:${Versions.UI_TEST_RUNNER_VERSION}"
    const val UI_TEST_RULES = "androidx.test:rules:${Versions.UI_TEST_RUNNER_VERSION}"
}

object AndroidSdk {
    const val min = 21
    const val compile = 33
    const val target = compile
}

object AndroidClient {
    const val appId = "com.android.diagnalmovies"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Kotlin {
    const val kotlinVersion = "1.8.0"
}

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "7.4.0"
        const val googleServices = "4.3.15"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.kotlinVersion}"
    const val kotlinTestPlugin = "rg.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val androidLibrary = "com.android.library"
    const val googleServicePlugin = "com.google.gms.google-services"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
}
