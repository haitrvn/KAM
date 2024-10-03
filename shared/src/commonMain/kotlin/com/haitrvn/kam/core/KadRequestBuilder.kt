package com.haitrvn.kam.core

import com.haitrvn.kam.core.KAdRequest

expect object KadRequestBuilder {
    fun build(): KAdRequest
}