package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.red_start_settings
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomTextField
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

sealed interface ValidationState {
    data object Ok : ValidationState
    data class Error(val massage: String? = "") : ValidationState
}

@Composable
fun SettingsTextFieldBlock(
    title: String,
    state: String,
    editable: Boolean = true,
    placeholder: String = "",
    necessarily: Boolean = false,
    hide: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    validator: (String) -> ValidationState = { ValidationState.Ok },
    onChange: (String) -> Unit,
) = Column(Modifier.fillMaxWidth()) {
    if (necessarily) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = vectorResource(Res.drawable.red_start_settings),
                tint = DevRushTheme.colors.baseRed,
                contentDescription = null,
                modifier = Modifier.size(11.dp)
            )
            Text(
                title,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2
            )
        }

    } else {
        Text(
            title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c2
        )
    }

    Gap(8)

    var text by remember { mutableStateOf(state) }
    var textVisibility by remember { mutableStateOf(hide) }
    var validationState: ValidationState by remember { mutableStateOf(ValidationState.Ok) }

    CustomTextField(
        text = text,
        enabled = editable,
        placeholder = placeholder,
        onTextChanged = { text = it },
        isTextVisibility = textVisibility,
        onHideText = { textVisibility = !textVisibility },
        keyboardType = keyboardType,
        showTrailingIcon = hide,
        isError = validationState != ValidationState.Ok,
        onFocusIsOff = {
            val validationResult = validator(text)
            validationState = validationResult
            if (validationResult == ValidationState.Ok) {
                onChange(text)
            }
        },
    )
    Gap(4)
    if (validationState != ValidationState.Ok) {
        Text(
            (validationState as ValidationState.Error).massage ?: "",
            color = DevRushTheme.colors.baseRed,
            style = DevRushTheme.typography.sfPro12
        )
    }
}

@Composable
fun SettingsTextFieldBlock(
    title: String,
    enabled: Boolean,
    onChange: (Boolean) -> Unit,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxWidth()
) {
    Text(
        title,
        style = DevRushTheme.typography.sfPro14,
        color = DevRushTheme.colors.c1
    )

    Switch(
        checked = enabled,
        onCheckedChange = onChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = DevRushTheme.colors.baseWhite,
            checkedTrackColor = DevRushTheme.colors.basePurple2,
            uncheckedThumbColor = DevRushTheme.colors.c3,
            uncheckedTrackColor = DevRushTheme.colors.c5,
            uncheckedBorderColor = DevRushTheme.colors.c3
        )
    )
}