plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id ("kotlin-android")
    id ("kotlin-parcelize")

    id ("com.google.dagger.hilt.android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id ("kotlin-kapt")

    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.rocketjournal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rocketjournal"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.0")
    //implementation("io.github.jan-tennert.supabase:gotrue-kt:2.2.0-alpha-2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


    implementation ("androidx.navigation:navigation-compose:2.7.7")
    implementation("io.ktor:ktor-client-android:2.3.8")

    implementation("io.ktor:ktor-client-cio:2.3.8")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation ("io.github.jan-tennert.supabase:postgrest-kt:2.1.6")
    implementation ("io.github.jan-tennert.supabase:storage-kt:2.1.6")
    implementation ("io.github.jan-tennert.supabase:gotrue-kt:2.1.6")
    implementation ("io.ktor:ktor-client-android:2.3.8")
    implementation ("io.ktor:ktor-client-core:2.3.8")
    implementation ("io.ktor:ktor-utils:2.3.8")
    implementation ("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //unit testing
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:3.+")
    testImplementation ("org.mockito:mockito-inline:3.+")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")


}

