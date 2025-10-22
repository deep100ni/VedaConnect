plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.DeepSoni.vedaconnect"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.DeepSoni.vedaconnect"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        // Android KTX
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)

        // Compose BOM (Bill of Materials) for consistent versions
        implementation(platform(libs.androidx.compose.bom))

        // Compose UI
        implementation(libs.androidx.activity.compose) // For ComponentActivity
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview) // For @Preview annotation

        // Compose Material Design
        implementation(libs.androidx.material3) // Material 3 components
        implementation("androidx.compose.material:material-icons-extended-android:1.7.0") // Or match your BOM version for stability
        // Note: It's generally better to let the BOM manage 'material-icons-extended' if possible,
        // but if you explicitly need a different version, keeping it like this is fine.
        // However, if your BOM is new enough, it might provide this.
        // For this example, I'm adjusting to 1.7.0 as 1.7.8 might be newer than a typical stable BOM.
        // If you explicitly added 1.7.8 to your libs.versions.toml, you'd use libs.androidx.materialIconsExtended

        // Compose Navigation
        implementation(libs.androidx.navigation.runtime.ktx)
        implementation(libs.androidx.navigation.compose)

        implementation(libs.androidx.navigation.common.ktx)
        implementation(libs.firebase.crashlytics.buildtools)
        implementation(libs.androidx.datastore.core)
        implementation("androidx.datastore:datastore-preferences:1.0.0")
        implementation("com.google.code.gson:gson:2.10.1")

        // Testing dependencies
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM for Compose testing
        androidTestImplementation(libs.androidx.ui.test.junit4)

        // Debugging and tooling
        debugImplementation(libs.androidx.ui.tooling) // For tooling in debug builds
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}

