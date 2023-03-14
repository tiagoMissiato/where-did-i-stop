@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.gradle)
}

android {
    namespace = "com.tiagomissiato.wheredidistop.core.testing"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = false
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Hilt Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    // Hilt and instrumented tests.
    implementation(libs.hilt.android.testing)
    kapt(libs.hilt.android.compiler)
    // Hilt and Robolectric tests.
    implementation(libs.hilt.android.testing)
    kapt(libs.hilt.android.compiler)

    // Local tests: jUnit, coroutines, Android runner
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)

    //Mockk
    implementation(libs.mockk)

    api(libs.androidx.test.core)
}