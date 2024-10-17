package com.haitrvn.kam.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform