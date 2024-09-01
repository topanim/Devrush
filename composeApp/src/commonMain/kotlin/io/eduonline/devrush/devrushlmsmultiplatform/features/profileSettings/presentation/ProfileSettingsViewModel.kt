package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.logout.LogoutRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository.ProfileRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.AppSettingsRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.profile.GetCachedProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.LogoutUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.SaveProfileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.domain.UploadFileUseCase
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsFields
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProfileSettingsViewModel(
    private val getCachedProfileUseCase: GetCachedProfileUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    private val saveProfileUseCase: SaveProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val appSettingsRepository: AppSettingsRepository,
    private val logoutRepository: LogoutRepository,
) : BaseViewModel<ProfileSettingsState, ProfileSettingsAction, ProfileSettingsEvent>(
    ProfileSettingsState()
), KoinComponent {
    val profileRepository: ProfileRepository by inject()
    override fun obtainEvent(viewEvent: ProfileSettingsEvent) {
        when (viewEvent) {
            is ProfileSettingsEvent.LoadProfile -> loadProfile(viewEvent.retried)
            is ProfileSettingsEvent.Save -> saveProfile(viewEvent.retried)
            ProfileSettingsEvent.Cancel -> {}
            ProfileSettingsEvent.Logout -> logout()
            ProfileSettingsEvent.DeleteAccount -> {}

            ProfileSettingsEvent.OpenChoosePhotoView ->
                viewAction = ProfileSettingsAction.ChosePhoto

            is ProfileSettingsEvent.ChangeTheme -> changeTheme(viewEvent.value)

            is ProfileSettingsEvent.ChangeNewsAnnouncementsPromotions ->
                changeNewsAnnouncementsPromotions(viewEvent.enabled)

            is ProfileSettingsEvent.ChoosePhoto -> uploadPhoto(viewEvent.data, viewEvent.retried)

            is ProfileSettingsEvent.EditName -> editFields {
                name = viewEvent.value
                nameWasEdited = viewEvent.value != viewState.profile!!.firstName
            }

            is ProfileSettingsEvent.EditSurname -> editFields {
                surname = viewEvent.value
                surnameWasEdited = viewEvent.value != viewState.profile!!.lastName
            }

            is ProfileSettingsEvent.EditPatronymic -> editFields {
                patronymic = viewEvent.value
                patronymicWasEdited = viewEvent.value != viewState.profile!!.middleName
            }

            is ProfileSettingsEvent.EditPhone -> editFields {
                phone = viewEvent.value
                phoneWasEdited = viewEvent.value != viewState.profile!!.phone
            }

            is ProfileSettingsEvent.EditGender -> editFields {
                gender = viewEvent.value
                genderWasEdited = viewEvent.value != viewState.profile!!.gender
            }

            is ProfileSettingsEvent.EditBirthdate -> editFields {
                birthdate = viewEvent.value
                birthdateWasEdited = viewEvent.value != viewState.profile!!.birthday
            }

            is ProfileSettingsEvent.EditPassword -> editFields {
                password = viewEvent.value
                passwordWasEdited = viewEvent.value.isNotEmpty()
            }

            is ProfileSettingsEvent.EditConfirmPassword -> editFields {
                confirmPassword = viewEvent.value
                confirmPasswordWasEdited = viewEvent.value.isNotEmpty()
            }

            ProfileSettingsEvent.ShowSaveProfileWarn -> viewAction =
                ProfileSettingsAction.ShowSaveProfileWarn

            ProfileSettingsEvent.ErrorCompare -> viewAction =
                ProfileSettingsAction.ShowErrorComparePassword
        }
    }

    private fun editFields(
        updateEditing: Boolean = true,
        config: ProfileSettingsFields.() -> Unit,
    ) {
        val updatedFields = viewState.fields!!.copy().apply(config)

        viewState = viewState.copy(
            fields = updatedFields,
            isProfileEditing = if (!updateEditing) viewState.isProfileEditing
            else updatedFields.anyFieldEdited()
        )
    }

    private fun changeNewsAnnouncementsPromotions(enabled: Boolean) {
        editFields(false) {
            newsAnnouncementsPromotions = enabled
        }

        viewModelScope.launch(Dispatchers.IO) {
            appSettingsRepository.updateSettings {
                enableNewsNotifications = enabled
            }
        }
    }


    private fun changeTheme(theme: Theme) {
        editFields(false) {
            this.theme = theme
        }

        viewModelScope.launch(Dispatchers.IO) {
            appSettingsRepository.updateSettings {
                setTheme(theme)
            }
        }

        viewAction = ProfileSettingsAction.ChangeTheme(theme)
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            logoutUseCase()
            withContext(Dispatchers.Main) {
                viewAction = ProfileSettingsAction.Logout
            }
            logoutRepository.logout()
        }
    }

    private fun saveProfile(retried: Boolean) {
        safeExecute(scope = viewModelScope,
            block = {
                saveProfileUseCase(viewState.fields!!)
                withContext(Dispatchers.Main) {
                    viewState = viewState.copy(isProfileEditing = false)
                }

//              loadProfile(false)
            }
        ) {
            viewState = viewState.copy(isProfileEditing = false)
        }
        viewAction = ProfileSettingsAction.ShowConfirmChangeProfile
    }


    private fun uploadPhoto(data: ByteArray, retried: Boolean) {
        viewState = viewState.copy(isPhotoLoading = true)

        safeExecute(scope = viewModelScope,
            block = {
                val uploadResponse = uploadFileUseCase(data)
                withContext(Dispatchers.Main) {
                    editFields {
                        imageId = uploadResponse.first
                        imageIdWasEdited = uploadResponse.first != viewState.profile!!.avatar?.id
                        imageCloudKey = uploadResponse.second
                    }
                    viewState = viewState.copy(isPhotoLoading = false)
                }
            }
        ) {
            viewState = viewState.copy(isPhotoLoading = false)
        }
    }

    private fun loadProfile(retried: Boolean) {
        safeExecute(scope = viewModelScope,
            block = {
                val cachedProfile = getCachedProfileUseCase()
                val appSettings = appSettingsRepository.getSettings()

                if (cachedProfile != null) {
                    withContext(Dispatchers.Main) { updateUI(cachedProfile, appSettings) }
                } else {
                    viewState = viewState.copy(profileLoadingState = ProfileLoadingState.Loading)
                    val profile = profileRepository.getProfileFromServer()
                    withContext(Dispatchers.Main) { updateUI(profile, appSettings) }
                }
            }
        ) {
            if (it is ExpiredTokenException && !retried) {
                loadProfile(retried)
            } else viewState = viewState.copy(
                profileLoadingState = ProfileLoadingState
                    .Error(it.message ?: "")
            )
        }
    }

    private fun updateUI(profile: GetMeResponse, appSettings: AppSettingsRepository.AppSettings) {
        viewState = viewState.copy(
            profile = profile,
            fields = ProfileSettingsFields(
                profile = profile,
                theme = appSettings.theme(),
                newsAnnouncementsPromotions = appSettings.enableNewsNotifications
            ),
            profileLoadingState = ProfileLoadingState.Success
        )
    }
}