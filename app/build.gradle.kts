import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.aagamshah.slipstreampicks"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aagamshah.slipstreampicks"
        minSdk = 24
        targetSdk = 35
        versionCode = 6         //05-06-2025
        versionName = "1.0.6"   //05-06-2025

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file(localProperties["RELEASE_STORE_FILE"] as String)
            storePassword = localProperties["RELEASE_STORE_PASSWORD"] as String
            keyAlias = localProperties["RELEASE_KEY_ALIAS"] as String
            keyPassword = localProperties["RELEASE_KEY_PASSWORD"] as String
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false //FALSE FOR PRODUCTION
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.configureEach {
        val apkName = "SlipstreamPicks-v${versionName}-${buildType.name}.apk"
        outputs.configureEach {
            (this as com.android.build.gradle.internal.api.ApkVariantOutputImpl).outputFileName =
                apkName
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    coreLibraryDesugaring(libs.core.desugaring)

    //Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.hilt)
    ksp(libs.hilt.android.compiler)

    //Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    //Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.interceptor)

    //Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    //Multidex
    implementation(libs.multidex)

    //LiveEdit
    implementation(libs.compose.runtime)
    implementation(libs.compose.livedata)
    implementation(libs.runtime.rxjava)

    //Pager
    implementation(libs.pager)

    //Lottie
    implementation(libs.lottie)

    //Room database
    implementation(libs.room.runtime)
    ksp(libs.room.ksp) // Use KSP instead of kapt
    implementation(libs.room.ktx)

    //Shared Preferences
    implementation(libs.shared.preferences)

    //Material Icons
    implementation(libs.material.icons)

    //Joda Time
    implementation(libs.joda.time)

    //AdMob
    implementation(libs.admob)

}

ksp {
    arg("hilt.enableAggregatingTask", "false")
}