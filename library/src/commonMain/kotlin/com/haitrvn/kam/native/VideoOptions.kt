package com.haitrvn.kam.native

expect class VideoOptions(
    clickToExpandRequested: Boolean?,
    customControlsRequested: Boolean?,
    startMuted: Boolean?,
) {
    val clickToExpandRequested: Boolean?
    val customControlsRequested: Boolean?
    val startMuted: Boolean?
}