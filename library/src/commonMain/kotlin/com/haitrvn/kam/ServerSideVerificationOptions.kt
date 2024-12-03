package com.haitrvn.kam

expect class ServerSideVerificationOptions {
    companion object {
        fun createInstance(
            block: ServerSideVerificationOptions.Builder.() -> Unit = {}
        ): ServerSideVerificationOptions
    }

    class Builder {
        fun setCustomData(customData: String): Builder
        fun setUserId(userId: String): Builder
        fun build(): ServerSideVerificationOptions
    }
}