package com.haitrvn.kam.core.model

data class InitializationStatus(
    val state: Map<String, AdapterStatus>,
)

data class AdapterStatus(
    val latency: Int,
    val initializationState: State,
    val description: String,
)

enum class State {
    NOT_READY,
    READY;
}