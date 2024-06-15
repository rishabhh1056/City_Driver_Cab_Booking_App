plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.customer_citydriver"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.customer_citydriver"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.locationdelegation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //text dimension
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    //country code picker
    implementation ("com.hbb20:ccp:2.7.1")

    //circule image
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //MapBox
    implementation("com.mapbox.maps:android:11.4.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")

    implementation ("com.google.android.material:material:1.3.0-alpha01")

    implementation ("com.airbnb.android:lottie:6.0.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")

    //Gson Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")





    //Mapbox search Apis
    implementation ("com.mapbox.search:autofill:2.2.0")
    //discover
    implementation ("com.mapbox.search:discover:2.2.0")
    //autocomplete
    implementation ("com.mapbox.search:place-autocomplete:2.2.0")

    implementation ("com.mapbox.search:offline:2.2.0")

    implementation ("com.mapbox.search:mapbox-search-android:2.2.0")

    implementation ("com.mapbox.search:mapbox-search-android-ui:2.2.0")

//    routines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")



//    implementation ("com.mapbox.navigation:core:2.7.1")
    implementation ("com.google.android.gms:play-services-location:21.0.1")






















}