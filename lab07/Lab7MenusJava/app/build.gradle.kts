plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ru.nstu.lab7.javaapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "ru.nstu.lab7.javaapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
