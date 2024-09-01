package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.colors
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    state: Boolean,
    birthdate: Long?,
    onClose: () -> Unit = {},
    onSelect: (Long) -> Unit,
) {
    val currentYear = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).year

    if (state) {
        val datePickerState = rememberDatePickerState(
            yearRange = 1900..currentYear - 12,
            initialSelectedDateMillis = birthdate
        )
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(

            colors = DatePickerDefaults.colors(
                containerColor = colors.background,
            ),
            onDismissRequest = onClose,
            confirmButton = {
                TextButton(
                    onClick = {
                        onSelect(datePickerState.selectedDateMillis!!)
                        onClose()
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(
                        DevRushTheme.strings.profileDatePickerOk,
                        color = colors.blue1
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onClose) {
                    Text(
                        DevRushTheme.strings.profileDatePicketCancel,
                        color = colors.blue1
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = colors.background,
                    titleContentColor = colors.c1,
                    dayContentColor = colors.c1,
                    disabledSelectedDayContentColor = colors.c2,
                    selectedDayContentColor = colors.c6,
                    disabledDayContentColor = colors.c2,
                    selectedDayContainerColor = colors.blue1,
                    selectedYearContainerColor = colors.blue1,
                    selectedYearContentColor = colors.baseWhite,
                    dayInSelectionRangeContentColor = colors.c2,
                    dividerColor = colors.c3,
                    todayContentColor = colors.blue1,
                    todayDateBorderColor = colors.blue1,
                    navigationContentColor = colors.c2,
                    weekdayContentColor = colors.c2,
                    currentYearContentColor = colors.blue1,
                    yearContentColor = colors.c2,
                    subheadContentColor = colors.c2,
                    headlineContentColor = colors.c2,
                    dateTextFieldColors = TextFieldDefaults.colors(
                        focusedTextColor = colors.c1,
                        unfocusedTextColor = colors.c1,
                        focusedContainerColor = if (LocalThemeIsDark.current) colors.c4 else colors.c6,
                        unfocusedContainerColor = if (LocalThemeIsDark.current) colors.c4 else colors.c6,
                        focusedIndicatorColor = colors.basePink1,
                        unfocusedIndicatorColor = colors.c5,
                        errorIndicatorColor = colors.baseRed,
                        focusedSupportingTextColor = colors.basePink1,
                        unfocusedSupportingTextColor = if (LocalThemeIsDark.current) colors.c4 else colors.c6,
                        disabledSupportingTextColor = colors.c2,
                        errorSupportingTextColor = colors.baseRed,
                        focusedLabelColor = colors.basePink1,
                        unfocusedLabelColor = colors.c2,
                        unfocusedPlaceholderColor = colors.c2,
                        disabledPlaceholderColor = colors.c2,
                        errorPlaceholderColor = colors.baseRed,
                        errorContainerColor = colors.c3
                    )
                )
            )
        }
    }
}
