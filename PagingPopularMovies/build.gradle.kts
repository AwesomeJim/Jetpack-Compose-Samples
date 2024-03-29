import java.io.FileInputStream
import java.util.Properties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id(libs.plugins.com.google.devtools.ksp.get().pluginId)
    alias(libs.plugins.com.google.dagger.hilt)
    id(libs.plugins.kapt.get().pluginId)
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

android {
    namespace = "com.awesomejim.popularmovies"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    defaultConfig {
        applicationId = "com.awesomejim.popularmovies"
        minSdk = libs.versions.min.sdk.get().toInt()
        targetSdk = libs.versions.target.sdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "MOVIE_API_KEY", localProperties.getProperty("MOVIE_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvm.target.get()
        freeCompilerArgs += listOf("-Xopt-in=kotlin.RequiresOptIn")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = libs.versions.jvm.target.get()
            suppressWarnings = true
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    //-------Compose--------------------------
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //-----------PAGING------------------
    implementation(libs.androidx.paging.common.ktx)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    //-----------COIL--------------------
    implementation(libs.coil.compose)


    //-----------ROOM--------------------
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.paging)

    //---------HILT----------------------
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    //---------Retrofit------------------
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.mock)
    implementation(libs.okhttp.logging.interceptor)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}