plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "xyz.teamgravity.pinlockcomposedemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "xyz.teamgravity.pinlockcomposedemo"
        minSdk = 21
        targetSdk = 34
        versionCode = 10300
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":pin_lock_compose"))
    // core

    // core
    implementation(libs.androidx.ktx)
    // compose ui
    implementation(libs.androidx.ui)

    // compose preview
    implementation(libs.androidx.ui.tooling.preview)

    // compose activity
    implementation(libs.androidx.activity.compose)

    // compose material3
    implementation(libs.androidx.material3)

    // lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
}