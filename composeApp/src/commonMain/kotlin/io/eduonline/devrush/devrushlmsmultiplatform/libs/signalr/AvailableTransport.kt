package io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr

import kotlinx.serialization.Serializable

@Serializable
internal class AvailableTransport(
    val transport: TransportEnumResponse,
    val transferFormats: List<TransferFormat>,
)