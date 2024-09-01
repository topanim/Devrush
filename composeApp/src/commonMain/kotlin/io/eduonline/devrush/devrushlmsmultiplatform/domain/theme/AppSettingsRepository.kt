package io.eduonline.devrush.devrushlmsmultiplatform.domain.theme

import co.touchlab.kermit.Logger
import io.github.xxfast.kstore.KStore
import kotlinx.serialization.Serializable

enum class Theme {
    Light,
    Dark,
    System
}

class AppSettingsRepository(
    private val store: KStore<AppSettings>,
) {
    @Serializable
    data class AppSettings(
        var theme: Int = Theme.System.ordinal,
        var enableNewsNotifications: Boolean = false,
        var isShowingOnboarding: Boolean = false
    ) {
        fun setTheme(value: Theme) {
            theme = value.ordinal
        }

        fun theme() = Theme.entries[theme]
    }

    suspend fun getSettings(): AppSettings = store.get()
        ?: throw Exception("There is no settings for this app")

    suspend fun resetSettings() = store.set(AppSettings())

    suspend fun updateSettings(
        config: AppSettings.() -> Unit,
    ) {
        val updatedSettings = getSettings().apply(config)
        store.set(updatedSettings)
        Logger.d(tag = "d", messageString = "updated settings: $updatedSettings")
    }
}