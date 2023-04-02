@file:Suppress("UnstableApiUsage")

plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        minSdk = AndroidSdk.min
        testInstrumentationRunner = AndroidClient.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // Android
    implementation(Libraries.CORE_KTX)
    implementation(Libraries.APPCOMPAT)

    // Architecture components
    implementation(Libraries.VIEWMODEL_KTX)

    // UI
    implementation(Libraries.MATERIAL_UI)
    implementation(Libraries.CONSTRAINT_LAYOUT)

    // Dependency injection
    implementation(Libraries.KOIN_CORE)
    implementation(Libraries.KOIN_ANDROID)

    // Coroutine
    implementation(Libraries.COROUTINE_ANDROID)

    // Timber
    implementation(Libraries.TIMBER)

    // Unit Testing
    testImplementation(Libraries.JUNIT)
    androidTestImplementation(Libraries.JUNIT_EXT)
    testImplementation(Libraries.MOCKK)

    // UI testing
    androidTestImplementation(Libraries.ESPRESSO_CORE)
    androidTestImplementation(Libraries.UI_TEST_RUNNER)
    androidTestImplementation(Libraries.UI_TEST_RULES)
}
