package com.haitrvn.kal.core

actual interface Error

actual class ErrorImpl actual constructor(
    code: Int,
    message: String,
    mediatedNetworkErrorCode: Int,
    mediatedNetworkErrorMessage: String,
    waterfall: Waterfall,
    requestLatencyMillis: Long
) : AdError {

}