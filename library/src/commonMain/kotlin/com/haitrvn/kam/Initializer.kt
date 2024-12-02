package com.haitrvn.kam

expect object Initializer {
    fun initialize(complete: (InitializationStatus) -> Unit = {})
}

expect class AdapterStatus {
    val latency: Int
    val state: State
    val description: String

    enum class State {
        NOT_READY, READY
    }
}

expect class InitializationStatus {
    val adapterStatusMap: Map<String, AdapterStatus>
}