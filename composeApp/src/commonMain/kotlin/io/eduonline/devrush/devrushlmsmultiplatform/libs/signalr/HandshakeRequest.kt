package io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr

import kotlinx.serialization.Serializable

@Serializable
internal class Handshake(val protocol: String, val version: Int)