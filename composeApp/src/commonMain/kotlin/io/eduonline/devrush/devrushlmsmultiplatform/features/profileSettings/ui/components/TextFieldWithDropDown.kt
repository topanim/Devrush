package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark

data class DropDownItem(
    val title: String,
    val value: String,
) {
    @Composable
    fun Content(onClick: (String) -> Unit) = DropdownMenuItem(
        text = {
            Text(
                title,
                style = DevRushTheme.typography.sfPro14,
                color = DevRushTheme.colors.c2,

                )
        },
        onClick = { onClick(value) }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldWithDropDown(
    title: String,
    placeholder: String,
    initialState: DropDownItem?,
    choices: List<DropDownItem>,
    onChange: (String) -> Unit,
) {
    val state = remember { mutableStateOf(initialState) }
    val showDropDown = remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxWidth()
    ) {
        Text(
            title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c2
        )

        Gap(8)

        Column(
            Modifier
                .border(
                    width = 1.dp,
                    color = DevRushTheme.colors.c5,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(45.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    if (LocalThemeIsDark.current) DevRushTheme.colors.c4
                    else DevRushTheme.colors.c6
                )
                .clickable { showDropDown.value = !showDropDown.value }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize().padding(start = 15.dp, end = 8.dp)
            ) {
                val hasSelectedItem = state.value != null

                Text(
                    state.value?.title ?: placeholder,
                    style = DevRushTheme.typography.sfPro14,
                    color = if (hasSelectedItem)
                        DevRushTheme.colors.c1 else DevRushTheme.colors.c2
                )
            }

            DropdownMenu(
                expanded = showDropDown.value,
                onDismissRequest = { showDropDown.value = false },
                offset = DpOffset(0.dp, (-8).dp),
                properties = PopupProperties(
                    usePlatformDefaultWidth = true,
                    clippingEnabled = true,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (LocalThemeIsDark.current) DevRushTheme.colors.c4
                        else DevRushTheme.colors.c6
                    )
            ) {
                choices.forEach { item ->
                    item.Content {
                        state.value = item
                        onChange(item.value)
                        showDropDown.value = false
                    }
                }
            }
        }
    }
}