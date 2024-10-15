import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    id("com.vanniktech.maven.publish") version "0.28.0"
    signing
}

group = "com.haitrvn"
version = "0.0.1-alpha"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("podfile/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        pod("Google-Mobile-Ads-SDK") {
            moduleName = "GoogleMobileAds"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.animation)
            implementation(compose.components.resources)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation("androidx.startup:startup-runtime:1.2.0")
            implementation("com.google.android.gms:play-services-ads:23.4.0")
        }
    }

    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    namespace = "com.haitrvn.kam"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

mavenPublishing {
    coordinates(
        groupId = "io.github.haitrvn",
        artifactId = "kam",
        version = "0.0.1"
    )
    pom {
        name.set("KAM (Kotlin AdMob)")
        description.set("Admob for Compose Multiplatform")
        url.set("https://github.com/haitrvn/KAM")
        licenses {
            license {
                name.set("Apache")
                url.set("https://opensource.org/license/apache-2-0")
            }
        }
        developers {
            developer {
                id.set("haitrvn")
                name.set("Hai Tran")
                organizationUrl.set("https://github.com/haitrvn")
            }
        }
        scm {
            url.set("https://github.com/haitrvn/KAM")
        }
    }
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()
}