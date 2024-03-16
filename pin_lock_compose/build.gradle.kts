import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
    signing
}

android {
    namespace = "xyz.teamgravity.pin_lock_compose"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }
    }
}

dependencies {

    // compose ui
    implementation(libs.androidx.ui)

    //test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // compose preview
    implementation(libs.androidx.ui.tooling.preview)

    // compose activity
    implementation(libs.androidx.activity.compose)

    // compose material3
    implementation(libs.androidx.material3)

    // compose constraint layout
    implementation(libs.androidx.constraintlayout.compose)
    // lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // security
    implementation(libs.androidx.security.crypto)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.zaneschepke"
            artifactId = "pin_lock_compose"
            version = providers.gradleProperty("pinVersionName").get()
            afterEvaluate {
                from(components["release"])
            }
            pom {
                name.set("Pin Lock Compose")
                description.set("Light library that is beautiful Pin Lock screen for Jetpack Compose. The library handles saving pin in Encrypted file. Integration is very easy and fast.")
                url.set("https://github.com/zaneschepke/pin-lock-compose")

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/zaneschepke/pin-lock-compose")
                    developerConnection.set("scm:git:https://github.com/zaneschepke/pin-lock-compose")
                    url.set("https://github.com/zaneschepke/pin-lock-compose")
                }
                developers {
                    organization {
                        name.set("Zane Schepke")
                        url.set("https://zaneschepke.com")
                    }
                    developer {
                        name.set("Zane Schepke")
                        email.set("support@zaneschepke.com")
                    }
                }
            }
        }
    }
    val properties = Properties().apply {
        try {
            load(rootProject.file("local.propertiess").reader())
        } catch (_: Exception){}

    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/zaneschepke/pin-lock-compose")
            credentials {
                username = properties.getProperty("GITHUB_USER")
                password = properties.getProperty("GITHUB_TOKEN")
            }
        }
        //disable for now
//        maven {
//            name = "sonatype"
//            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = System.getenv("MAVEN_CENTRAL_USER") ?: properties.getProperty("MAVEN_CENTRAL_USER")
//                password = System.getenv("MAVEN_CENTRAL_PASS") ?: properties.getProperty("MAVEN_CENTRAL_PASS")
//            }
//        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}