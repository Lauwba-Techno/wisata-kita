plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lauwba.wisatakita"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lauwba.wisatakita"
        minSdk = 24
        targetSdk = 34
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //add google maps libs
    implementation ("com.google.android.gms:play-services-maps:18.1.0")

    //add glide libs
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

    //rounded image
    implementation ("com.makeramen:roundedimageview:2.3.0")

    //view pager 2
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    //fresco imageviewer
    implementation("com.github.stfalcon-studio:StfalconImageViewer:v1.0.1")

    //photoview
    implementation ("com.github.chrisbanes:PhotoView:2.3.0")

    //android browser aka custom tabs
    implementation ("androidx.browser:browser:1.6.0")
}