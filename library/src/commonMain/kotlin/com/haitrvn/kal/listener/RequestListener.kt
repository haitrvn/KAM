package com.haitrvn.kal.listener

expect fun interface RequestListener {
    fun onAdRequestStarted(value: String)
}