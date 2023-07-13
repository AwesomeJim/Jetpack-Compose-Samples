import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isInfoAsWarnings

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.com.android.application.get().pluginId)
    id(libs.plugins.org.jetbrains.kotlin.android.get().pluginId)
}


android {
    namespace = "com.example.jetnews"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    defaultConfig {
        applicationId = "com.example.jetnews"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        // We use a bundled debug keystore, to allow debug builds from CI to be upgradable
        if (rootProject.file("debug.keystore").exists()) {
            getByName("debug") {
                storeFile = rootProject.file("debug.keystore")
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }
    }

    buildTypes {
        debug {
            // signingConfig signingConfigs.debug
        }


        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    // Tests can be Robolectric or instrumented tests
    sourceSets {
        val sharedTestDir = "src/sharedTest/java"
        this.getByName("test") {
            java {
                srcDirs(sharedTestDir)
            }
        }
        getByName("androidTest") {
            java {
                srcDirs(sharedTestDir)
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
        }
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    testImplementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.runtime.livedata)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    implementation(libs.androidx.navigation.compose)

    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.runner)

    // TODO: Bump to latest after Espresso 3.5.0 goes stable
    //  (due to https://github.com/robolectric/robolectric/issues/6593)
    testImplementation(libs.robolectric)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        // Treat all Kotlin warnings as errors (disabled by default)
        allWarningsAsErrors =
            if (project.hasProperty("warningsAsErrors")) project.isInfoAsWarnings() else false

        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        // Enable experimental coroutines APIs, including Flow
        freeCompilerArgs += "-opt-in=kotlin.Experimental"

        // Set JVM target to 1.8
        jvmTarget = "1.8"
    }
}

tasks.withType(Test::class.java) {
    systemProperty("robolectric.logging", "stdout")
}
