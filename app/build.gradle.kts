plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "${Constants.PACKAGE}.pinlockcomposedemo"
    compileSdk = Constants.COMPILE_SDK

    defaultConfig {
        applicationId = "${Constants.PACKAGE}.pinlockcomposedemo"
        minSdk = Constants.MIN_SDK
        targetSdk = Constants.COMPILE_SDK
        versionCode = Constants.VERSION_CODE
        versionName = Constants.VERSION_NAME

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
        jvmTarget = Constants.JVM_TARGET
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Constants.COMPILER_VERSION
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