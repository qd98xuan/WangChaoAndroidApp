plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt") version "1.5.30"
}

android {
    // jniLibs.srcDirs("libs")
    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }
    namespace = "com.hx.wangchao"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.hx.optical_signal_transmission"
        minSdk = 21
        targetSdk = 34

        versionCode = 1
        versionName = "1.0.0"

//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            storeFile = file("../health_device_pad.jks")
            storePassword = "sanbaishuo123"
            keyAlias = "key0"
            keyPassword = "sanbaishuo123"
        }
        getByName("debug") {
            storeFile = file("../health_device_pad.jks")
            storePassword = "sanbaishuo123"
            keyAlias = "key0"
            keyPassword = "sanbaishuo123"
        }
    }
    buildTypes {
        release {
            buildConfigField("boolean","DEBUG","false")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("boolean","DEBUG","true")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation("androidx.compose.runtime:runtime-livedata:1.3.2")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val accompanist_version = "0.15.0"
    implementation("com.google.accompanist:accompanist-insets:$accompanist_version")
    implementation("com.google.accompanist:accompanist-insets-ui:$accompanist_version")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist_version")

    // coil
    implementation("io.coil-kt:coil-compose:2.3.0")

    // mqttAndroid
    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.2")
    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")

    implementation(project(":base_library"))
//    implementation(project(":FaceCollectModule"))
    implementation("com.github.mik3y:usb-serial-for-android:3.7.0")
//    implementation("io.github.xmaihh:serialport:2.1.1")
    implementation("com.github.licheedev:Android-SerialPort-API:2.0.0")
    implementation("io.github.xmaihh:serialport:2.1.1")
    val room_version = "2.6.1"
    //    room数据库
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    kapt("androidx.room:room-compiler:$room_version")
}