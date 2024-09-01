package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.CustomDatePickerDialog
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import io.eduonline.devrush.devrushlmsmultiplatform.utils.formatTime
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToMillis
import io.eduonline.devrush.devrushlmsmultiplatform.utils.simpleFormatTime

@Composable
fun DatePickerTextField(
    title: String,
    initialState: String?,
    placeholder: String,
    onChange: (String) -> Unit,
) {
    val state = remember {
        mutableStateOf(
            if (initialState == null) null
            else parseStringToMillis(initialState)
        )
    }
    val showPicker = remember { mutableStateOf(false) }

    val themeIsDark = LocalThemeIsDark.current
    val containerColor = if (themeIsDark) DevRushTheme.colors.c4
    else DevRushTheme.colors.c6

    CustomDatePickerDialog(
        showPicker.value,
        state.value,
        onClose = { showPicker.value = false },
    ) {
        state.value = it
        val formattedTime = formatTime(it)
        if (formattedTime != initialState) onChange(formattedTime)
    }

    Column(
        Modifier.fillMaxWidth()
    ) {
        Text(
            title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c2
        )

        Gap(8)

        Box(
            Modifier
                .border(
                    width = 1.dp,
                    color = DevRushTheme.colors.c5,
                    shape = RoundedCornerShape(12.dp)
                )
                .height(45.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(containerColor)
                .clickable { showPicker.value = true }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize().padding(start = 15.dp, end = 8.dp)
            ) {
                val isSelected = state.value != null

                Text(
                    if (isSelected) simpleFormatTime(state.value!!) else placeholder,
                    style = DevRushTheme.typography.sfPro14,
                    color = if (isSelected) DevRushTheme.colors.c1 else DevRushTheme.colors.c2
                )

                Icon(
                    contentDescription = null,
                    imageVector = Icons.Default.DateRange,
                    tint = DevRushTheme.colors.c3,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}
