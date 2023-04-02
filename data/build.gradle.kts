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
}

dependencies {

    implementation(project(path = ":domain"))
    implementation(project(path = ":core"))

    // Coroutine
    implementation(Libraries.COROUTINE_ANDROID)

    // Dependency injection
    implementation(Libraries.KOIN_CORE)
    implementation(Libraries.KOIN_ANDROID)

    // Timber
    implementation(Libraries.TIMBER)

    // Converter
    implementation(Libraries.GSON_CONVERTER)

    // Unit Testing
    testImplementation(Libraries.JUNIT)
    androidTestImplementation(Libraries.JUNIT_EXT)
    testImplementation(Libraries.MOCKK)
}
