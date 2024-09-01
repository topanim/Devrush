package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components.TextFieldComponents

import androidx.compose.runtime.Composable
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.DropDownItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components.TextFieldWithDropDown
import io.eduonline.devrush.devrushlmsmultiplatform.features.registration.presentation.model.RegisterEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun GenderField(onEvent: (RegisterEvent) -> Unit) {
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
        initialState = choices[0],
        choices = choices,
    ) {
        onEvent.invoke(RegisterEvent.SetGender(it))
    }

}