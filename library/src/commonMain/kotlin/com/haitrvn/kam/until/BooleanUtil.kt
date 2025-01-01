package com.haitrvn.kam.until

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true