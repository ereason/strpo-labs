plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ru.nstu.lab6.kotlinapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "ru.nstu.lab6.kotlinapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.coordinatorlayout)
    implementation(libs.material)
}
