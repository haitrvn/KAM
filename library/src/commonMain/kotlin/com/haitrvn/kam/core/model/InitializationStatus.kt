package com.haitrvn.kam.core.model

data class AdapterStatus(
    val latency: Int,
    val initializationState: State,
    val description: String,
)

enum class State {
    NOT_READY,
    READY;
}