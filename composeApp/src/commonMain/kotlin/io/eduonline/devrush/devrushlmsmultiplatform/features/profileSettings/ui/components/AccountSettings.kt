package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.validation.ValidationErrors
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.presentation.models.ProfileSettingsState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun ColumnScope.AccountSettings(
    state: ProfileSettingsState,
    onEvent: (ProfileSettingsEvent) -> Unit = {},
) {
    SettingsTextFieldBlock(
        DevRushTheme.strings.profileEmail,
        placeholder = DevRushTheme.strings.profileEmail,
        state = state.fields!!.email,
        editable = false,
        necessarily = true
    ) { }

    Gap(16)

    SettingsTextFieldBlock(
        DevRushTheme.strings.profileName,
        placeholder = DevRushTheme.strings.profileName,
        state = state.fields.name,
        necessarily = true,
        validator = {
            if(it.isEmpty()){
                ValidationState.Error("")
            } else ValidationState.Ok
        }
    ) { onEvent(ProfileSettingsEvent.EditName(it)) }

    Gap(16)

    SettingsTextFieldBlock(
        DevRushTheme.strings.profileSurname,
        placeholder = DevRushTheme.strings.profileSurname,
        state = state.fields.surname ?: ""
    ) { onEvent(ProfileSettingsEvent.EditSurname(it)) }

    Gap(16)

    SettingsTextFieldBlock(
        DevRushTheme.strings.profilePatronymic,
        placeholder = DevRushTheme.strings.profilePatronymic,
        state = state.fields.patronymic ?: "",
    ) { onEvent(ProfileSettingsEvent.EditPatronymic(it)) }

    Gap(16)

    PhoneTextField(
        DevRushTheme.strings.profilePhone,
        state = state.fields.phone ?: "",
        necessarily = true,
    ) { onEvent(ProfileSettingsEvent.EditPhone(it)) }

    Gap(16)

    DatePickerTextField(
        DevRushTheme.strings.profileBirthDate,
        placeholder = DevRushTheme.strings.profileBirthDate,
        initialState = state.profile?.birthday,
    ) { onEvent(ProfileSettingsEvent.EditBirthdate(it)) }

    Gap(16)

    val choices = listOf(
        DropDownItem(
            DevRushTheme.strings.profileGenderMale,
            "male"
        ),
        DropDownItem(
            DevRushTheme.strings.profileGenderFemale,
            "female"
        )
    )

    TextFieldWithDropDown(
        DevRushTheme.strings.profileGender,
        placeholder = DevRushTheme.strings.profileGender,
        initialState = choices.firstOrNull { it.value == state.fields.gender },
        choices = choices,
    ) { onEvent(ProfileSettingsEvent.EditGender(it)) }

}