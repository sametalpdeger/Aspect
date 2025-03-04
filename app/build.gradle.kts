plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.apollo)
}



android {
    apollo {
        service("service") {
            packageName.set("com.example")
            introspection {
                endpointUrl.set("http://192.168.1.113:3200/graphql")
                schemaFile.set(file("src/main/graphql/schema.graphqls"))
            }
        }
    }

    namespace = "com.example.aspectchat"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.aspectchat"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.aspectchat.CustomTestRunner"
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
        buildFeatures {
            compose = true
        }
    }

    dependencies {
        implementation(libs.apache.commons.codec)

        implementation(libs.bouncycastle.bcprov.jdk18on)

        implementation(libs.androidx.junit.ktx)
        ksp(libs.hilt.compiler)

        implementation(libs.apollo.runtime)

        implementation(libs.okhttp3)
        implementation(libs.retrofit2)
        implementation(libs.retrofit2.kotlinx.serialization.converter)
        implementation(libs.retrofit.gson)
        implementation(libs.okhttp.logging.interceptor)


        implementation(libs.androidx.hilt.navigation.compose)
        implementation(libs.hilt.android)

        implementation(libs.kotlinx.serialization.json)

        implementation(libs.androidx.datastore.preferences)
        implementation(libs.androidx.navigation.compose)
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
        testImplementation(libs.hilt.android.testing)
        testImplementation(libs.hilt.ext.compiler)
        implementation(libs.dagger.hiltandroidplugin)

        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)
    }
}
dependencies {
    implementation(libs.androidx.runner)
    implementation(libs.hilt.android.testing)
    androidTestImplementation(libs.hilt.android.testing)
}
