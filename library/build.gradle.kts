import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.vanniktech.mavenPublish)
}

group = "io.github.haitrvn"
version = "0.0.1"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishAllLibraryVariants()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        noPodspec()
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("podfile/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        pod("AppLovinSDK") {
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.ui)
            api(compose.animation)
            api(compose.components.resources)
            api("androidx.lifecycle:lifecycle-common:2.8.7")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            api(libs.startup.runtime)
//            api(libs.play.services.ads)
            api("com.applovin:applovin-sdk:13.0.1")
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
    namespace = "com.haitrvn.kal"
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
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates(group.toString(), "kal", version.toString())
    pom {
        name = "kal (Kotlin Applovin)"
        description = "Admob for Compose Multiplatform"
        inceptionYear = "2024"
        url = "https://github.com/haitrvn/kal"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "http://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "haitrvn"
                name = "Tran Van Hai"
                url = "https://github.com/haitrvn/"
            }
        }
        scm {
            url.set("https://github.com/haitrvn/kal")
        }
    }
}