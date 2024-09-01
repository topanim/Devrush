package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.eye
import devrush_multiplatform.composeapp.generated.resources.eye_closed
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

//showTrailingIcon = Отвечает за показ иконок в конце поле - по умолчанию false
//isTextVisibility в скупе OnHideText отвечает за скрытие и показа пароля - по умолчанию все видно.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    placeholder: String,
    showTrailingIcon: Boolean = false,
    isTextVisibility: Boolean = true,
    enabled: Boolean = true,
    onHideText: () -> Unit = {},
    isError: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    onFocusIsOff: () -> Unit = {},
) {
    val focus = LocalFocusManager.current
    val themeIsDark = LocalThemeIsDark.current
    val containerColor = if (themeIsDark) DevRushTheme.colors.c4
    else DevRushTheme.colors.c6

    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    val visualTransformation =
        if (isTextVisibility && showTrailingIcon) PasswordVisualTransformation()
        else VisualTransformation.None
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        singleLine = true,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .onFocusChanged {
                if (!it.isFocused) onFocusIsOff()
            },
        textStyle = DevRushTheme.typography.sfPro14.copy(color = DevRushTheme.colors.c1),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions {
            focus.clearFocus()
        },
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(DevRushTheme.colors.basePink1),
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                placeholder = {
                    if (text.isBlank())
                        Text(
                            placeholder,
                            color = DevRushTheme.colors.c2,
                            style = DevRushTheme.typography.sfPro14
                        )
                },
                visualTransformation = visualTransformation,
                trailingIcon = {
                    if (showTrailingIcon) {
                        Icon(
                            imageVector = vectorResource(
                                if (isTextVisibility) Res.drawable.eye_closed
                                else Res.drawable.eye
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable(onClick = onHideText)
                                .padding(7.dp),
                            tint = DevRushTheme.colors.c3
                        )
                    }
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        shape = RoundedCornerShape(12.dp),
                        focusedBorderThickness = 1.dp,
                        unfocusedBorderThickness = 1.dp,
                        enabled = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = DevRushTheme.colors.c1,
                            unfocusedTextColor = DevRushTheme.colors.c1,
                            focusedContainerColor = containerColor,
                            unfocusedContainerColor = containerColor,
                            disabledContainerColor = containerColor,
                            focusedBorderColor = DevRushTheme.colors.basePink1,
                            unfocusedBorderColor = DevRushTheme.colors.c5,
                            errorBorderColor = DevRushTheme.colors.baseRed,
                            errorContainerColor = DevRushTheme.colors.c6,
                        ),
                        interactionSource = interactionSource,
                        isError = isError
                    )
                },
                contentPadding = OutlinedTextFieldDefaults.contentPadding(
                    top = 0.dp,
                    bottom = 0.dp
                ),
                interactionSource = interactionSource,
            )
        }
    )
}

