package com.haitrvn.kam

expect class ServerSideVerificationOptions(
    customData: String,
    userId: String,
) {
    val customData: String
    val userId: String
}