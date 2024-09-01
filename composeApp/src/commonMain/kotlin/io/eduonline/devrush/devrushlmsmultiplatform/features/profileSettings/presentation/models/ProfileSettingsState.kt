package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors

data class ProfileSettingsState(
    val profileLoadingState: ProfileLoadingState = ProfileLoadingState.Loading,
    val profile: GetMeResponse? = null,
    val fields: ProfileSettingsFields? = null,
    var isProfileEditing: Boolean = false,
    var isPhotoLoading: Boolean = false,
    var validationState:ValidationErrors? = null
)

sealed interface ProfileLoadingState {
    data object Loading : ProfileLoadingState
    data object Success : ProfileLoadingState
    data class Error(val message: String) : ProfileLoadingState
}


data class ProfileSettingsFields(
    private val profile: GetMeResponse,
    var theme: Theme,
    var newsAnnouncementsPromotions: Boolean,
    var phone: String? = profile.phone,
    var email: String = profile.email,
    var name: String = profile.firstName,
    var surname: String? = profile.lastName,
    var patronymic: String? = profile.middleName,
    var birthdate: String? = profile.birthday,
    var imageId: String? = profile.avatar?.id,
    var imageCloudKey: String? = profile.avatar?.cloudKey,
    var gender: String? = profile.gender,
    var password: String = "",
    var confirmPassword: String = "",
) {
    var phoneWasEdited: Boolean = false
    var emailWasEdited: Boolean = false
    var nameWasEdited: Boolean = false
    var surnameWasEdited: Boolean = false
    var patronymicWasEdited: Boolean = false
    var birthdateWasEdited: Boolean = false
    var imageIdWasEdited: Boolean = false
    var imageCloudKeyWasEdited: Boolean = false
    var genderWasEdited: Boolean = false
    var passwordWasEdited: Boolean = false
    var confirmPasswordWasEdited: Boolean = false

    fun anyFieldEdited(): Boolean {
        return phoneWasEdited
                || emailWasEdited
                || nameWasEdited
                || surnameWasEdited
                || patronymicWasEdited
                || birthdateWasEdited
                || imageIdWasEdited
                || imageCloudKeyWasEdited
                || genderWasEdited
                || passwordWasEdited
                || confirmPasswordWasEdited
    }

    fun clear() {
        phone = profile.phone
        email = profile.email
        name = profile.firstName
        surname = profile.lastName
        patronymic = profile.middleName
        birthdate = profile.birthday
        imageCloudKey = profile.avatar?.cloudKey
        gender = profile.gender
        password = ""
        confirmPassword = ""
    }
}