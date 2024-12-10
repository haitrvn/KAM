package com.haitrvn.kam.native

import com.google.android.gms.ads.MuteThisAdReason as AndroidMuteThisAdReason

actual class MuteThisAdReason(
    actual val description: String,
) {
    constructor(android: AndroidMuteThisAdReason) : this(
        description = android.description
    )

    val android: AndroidMuteThisAdReason by lazy {
        AndroidMuteThisAdReason { description }
    }
}