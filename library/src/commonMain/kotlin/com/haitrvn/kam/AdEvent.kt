package com.haitrvn.kam

sealed interface AdEvent {
    data object Clicked: AdEvent
    data object Closed: AdEvent
    data class FailedToLoad(val error: AdError): AdEvent
    data object Impression: AdEvent
    data object Loaded: AdEvent
    data object Opened: AdEvent
    data object SwipeGestureClicked: AdEvent
}