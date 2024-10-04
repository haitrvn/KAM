# KMM AdMob Integration

[![Apache 2.0 License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

This repository demonstrates how to implement **AdMob** in a **Kotlin Multiplatform Mobile (KMM)** project, providing support for **Android** and **iOS** platforms. Currently, only **Interstitial Ads** are fully supported. Other ad formats such as Banner Ads, Native Ads, and Rewarded Ads are under development.

## Features

| Feature               | Android | iOS  | Status           |
|-----------------------|---------|------|------------------|
| **Interstitial Ads**   | ✅      | ✅   | Implemented      |
| **Banner Ads**         | 🚧      | 🚧   | In Progress      |
| **Native Ads**         | 🚧      | 🚧   | In Progress      |
| **Rewarded Ads**       | 🚧      | 🚧   | In Progress      |

## Getting Started

To integrate **AdMob** in your KMM project, follow these steps:

1. **Add Dependencies**  
   In your shared KMM module's `build.gradle.kts` or `build.gradle`:
   
   ```kotlin
   kotlin {
       android()
       ios()

       sourceSets {
           val commonMain by getting {
               dependencies {
                   // Add common dependencies for AdMob (if any)
               }
           }
           val androidMain by getting {
               dependencies {
                   implementation("com.google.android.gms:play-services-ads:20.5.0")
               }
           }
           val iosMain by getting {
               dependencies {
                   // iOS AdMob dependencies
                   implementation("io.github.appsflyer:af-ios-sdk:5.4.0")
               }
           }
       }
   }
