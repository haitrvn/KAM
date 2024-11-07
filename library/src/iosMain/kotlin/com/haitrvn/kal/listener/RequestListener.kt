package com.haitrvn.kal.listener

actual fun interface RequestListener {
    actual fun onAdRequestStarted(value: String)
}