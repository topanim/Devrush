package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.commit_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.dsa
import devrush_multiplatform.composeapp.generated.resources.sda
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreenTextField(
    header: String,
    placeholder: String,
    text: String,
    onTextChanged: (String) -> Unit,
    isHidden: Boolean,
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val focus = LocalFocusManager.current
    val isHiddenPassword = remember { mutableStateOf(true) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val currentTheme = LocalTheme.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = header,
            color = DevRushTheme.colors.c2
        )
        Gap(5)
        BasicTextField(
            value =text,
            onValueChange = onTextChanged,
            singleLine = true,
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            textStyle = DevRushTheme.typography.sfPro14.copy(color = DevRushTheme.colors.c1),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            keyboardActions = KeyboardActions {
                focus.clearFocus()
            },
            visualTransformation =
            if (isHidden) {
                if (isHiddenPassword.value) PasswordVisualTransformation()
                else VisualTransformation.None
            } else VisualTransformation.None,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(DevRushTheme.colors.basePink1),
            decorationBox = { innerTextField ->
                OutlinedTextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = innerTextField,
                    placeholder = {
                        if (text.isBlank())
                            Text(
                                text = placeholder,
                                color = DevRushTheme.colors.c2,
                                style = DevRushTheme.typography.sfPro14,

                                )
                    },
                    trailingIcon = {
                        if (isHidden) {
                            Icon(
                                imageVector = vectorResource(if (!isHiddenPassword.value) Res.drawable.dsa else Res.drawable.sda),
                                contentDescription = null,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .padding(7.dp)
                                    .clickable {
                                        isHiddenPassword.value = !isHiddenPassword.value
                                    },
                                tint = DevRushTheme.colors.c3
                            )
                        }
                    },
                    isError = isError,
                    singleLine = true,
                    enabled = true,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            shape = RoundedCornerShape(12.dp),
                            focusedBorderThickness = 1.dp,
                            unfocusedBorderThickness = 1.dp,
                            enabled = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DevRushTheme.colors.c1,
                                unfocusedTextColor = DevRushTheme.colors.c1,
                                focusedContainerColor =
                                if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
                                else DevRushTheme.colors.c6,
                                unfocusedContainerColor = if (
                                    LocalThemeIsDark.current || currentTheme.value == Theme.Dark
                                ) DevRushTheme.colors.c4
                                else DevRushTheme.colors.c6,
                                focusedBorderColor = DevRushTheme.colors.basePink1,
                                unfocusedBorderColor = DevRushTheme.colors.c5,
                                errorBorderColor = DevRushTheme.colors.baseRed,
                                errorContainerColor = DevRushTheme.colors.c6,
                            ),
                            interactionSource = interactionSource,
                            isError = isError
                        )
                    },
                    visualTransformation =
                    if (isHidden) {
                        if (isHiddenPassword.value) PasswordVisualTransformation()
                        else VisualTransformation.None
                    } else VisualTransformation.None,
                    contentPadding = OutlinedTextFieldDefaults.contentPadding(
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                    interactionSource = interactionSource

                )
            }
        )

    }
}
