package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.domain

import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.NotificationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.markAllAsRead.MarkAllAsReadRequest

class PushMarkAllAsReadUseCase(
    private val api: NotificationModule,
    private val authRepository: AuthTokenRepository,
) {
    suspend operator fun invoke() {
        api.markAllAsRead(
            MarkAllAsReadRequest(authRepository.getTokens().accessToken)
        )
    }
}