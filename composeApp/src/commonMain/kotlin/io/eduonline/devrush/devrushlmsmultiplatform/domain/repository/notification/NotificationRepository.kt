package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.notification

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.NotificationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.GetNotificationsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification

class NotificationRepository(
    private val api: NotificationModule,
    private val database: AppDatabase,
    private val authRepository: AuthTokenRepository,
) {

    suspend fun saveNotifications(notifications: List<Notification>) {
        database.getNotificationDao().insert(notifications.map { it.toNotificationDBO() })
    }


    suspend fun clean() {
        database.getNotificationDao().clean()
    }


    suspend fun getAllFromServer(): List<Notification> {
        val notifications = api.getNotifications(
            GetNotificationsRequest(authRepository.getTokens().accessToken)
        ).checkedRefresh(authRepository).checkedResult().items

        return notifications
    }

    suspend fun gelAllFromDatabase(): List<Notification> {
        return database.getNotificationDao().getAll().map { it.toNotification() }
    }
}