package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.utils

import androidx.compose.runtime.Composable
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

enum class MonthName {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    OCTOBER,
    NOVEMBER,
    DECEMBER,
}

@Composable
fun getMonthName(month: String): String {
    return when (month) {
        MonthName.JANUARY.name -> DevRushTheme.strings.january
        MonthName.FEBRUARY.name -> DevRushTheme.strings.february
        MonthName.MARCH.name -> DevRushTheme.strings.march
        MonthName.APRIL.name -> DevRushTheme.strings.april
        MonthName.MAY.name-> DevRushTheme.strings.may
        MonthName.JUNE.name-> DevRushTheme.strings.june
        MonthName.JULY.name -> DevRushTheme.strings.july
        MonthName.AUGUST.name -> DevRushTheme.strings.august
        MonthName.SEPTEMBER.name -> DevRushTheme.strings.september
        MonthName.OCTOBER.name -> DevRushTheme.strings.october
        MonthName.NOVEMBER.name -> DevRushTheme.strings.november
        MonthName.DECEMBER.name -> DevRushTheme.strings.december
        else -> {""}
    }
}