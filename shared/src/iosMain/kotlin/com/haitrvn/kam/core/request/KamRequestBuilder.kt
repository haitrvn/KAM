package com.haitrvn.kam.core.request

import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual object KamRequestBuilder {
    actual fun build(): KamRequest {
         return GADRequest()
    }
}