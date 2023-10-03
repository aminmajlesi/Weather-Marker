plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.hamrahchallenge8"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.hamrahchallenge8"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //google maps
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.5.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")


    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.2")

    // Room
    implementation("androidx.room:room-runtime:2.4.2")
    kapt ("androidx.room:room-compiler:2.4.2")

}