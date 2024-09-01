package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun RegisterTopBar(){
    DevRushTopAppBar(
        title = DevRushTheme.strings.registrationTitle,
        backButton = false,
        style = DevRushTheme.typography.sfProBold36,
        alignment = Alignment.TopStart,
        clipText = false,
        padding = 0
    )
}