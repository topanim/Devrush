// Licensed to the .NET Foundation under one or more agreements.
// The .NET Foundation licenses this file to you under the MIT license.
package io.eduonline.devrush.devrushlmsmultiplatform.libs.signalr

/**
 * Indicates the state of the [HubConnection].
 */
enum class HubConnectionState {
    CONNECTED,
    DISCONNECTED,
    CONNECTING,
    RECONNECTING,
}