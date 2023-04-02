import java.util.Properties
import java.io.FileInputStream

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.googleServicePlugin)
}

android {
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = AndroidClient.appId
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = AndroidClient.versionCode
        versionName = AndroidClient.versionName

        testInstrumentationRunner = AndroidClient.testRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://storage.googleapis.com/carousell-interview-assets/android/\""
            )
        }
        getByName("debug") {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://storage.googleapis.com/carousell-interview-assets/android/\""
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
    implementation(project(path = ":domain"))
    implementation(project(path = ":core"))
    implementation(project(path = ":home"))
    implementation(project(path = ":data"))

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

    // Converter
    implementation(Libraries.GSON_CONVERTER)

    // Coroutine
    implementation(Libraries.COROUTINE_ANDROID)

    // Firebase
    implementation(platform(Libraries.FIREBASE_BOM))

    // Timber
    implementation(Libraries.TIMBER)

    // Unit Testing
    testImplementation(Libraries.JUNIT)
    androidTestImplementation(Libraries.JUNIT_EXT)
    testImplementation(Libraries.MOCKK)
    testImplementation(Libraries.COROUTINE_TESTING)

    // UI testing
    androidTestImplementation(Libraries.ESPRESSO_CORE)
    androidTestImplementation(Libraries.UI_TEST_RUNNER)
    androidTestImplementation(Libraries.UI_TEST_RULES)
}
