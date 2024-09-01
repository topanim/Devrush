package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme


sealed interface ProfileSettingsEvent {
    data object OpenChoosePhotoView : ProfileSettingsEvent
    class ChoosePhoto(val data: ByteArray, val retried: Boolean = false) : ProfileSettingsEvent

    data object Logout : ProfileSettingsEvent
    data object DeleteAccount : ProfileSettingsEvent

    data class Save(val retried: Boolean = false) : ProfileSettingsEvent
    data object ErrorCompare : ProfileSettingsEvent
    data object Cancel : ProfileSettingsEvent
    data class LoadProfile(val retried: Boolean = false) : ProfileSettingsEvent

    data class EditName(val value: String) : ProfileSettingsEvent
    data class EditSurname(val value: String) : ProfileSettingsEvent
    data class EditPatronymic(val value: String) : ProfileSettingsEvent
    data class EditPhone(val value: String) : ProfileSettingsEvent
    data class EditBirthdate(val value: String) : ProfileSettingsEvent
    data class EditGender(val value: String) : ProfileSettingsEvent
    data class EditPassword(val value: String) : ProfileSettingsEvent
    data class EditConfirmPassword(val value: String) : ProfileSettingsEvent

    data object ShowSaveProfileWarn : ProfileSettingsEvent

    data class ChangeTheme(val value: Theme) : ProfileSettingsEvent
    data class ChangeNewsAnnouncementsPromotions(val enabled: Boolean) : ProfileSettingsEvent

}