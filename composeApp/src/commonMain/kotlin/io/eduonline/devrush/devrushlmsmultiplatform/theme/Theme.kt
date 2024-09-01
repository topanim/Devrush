package io.eduonline.devrush.devrushlmsmultiplatform.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.sp
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme


internal val LocalThemeIsDark = compositionLocalOf { true }
internal val LocalLanguage = compositionLocalOf { mutableStateOf(english) }


@Composable
internal fun AppTheme(
    theme: Theme,
    content: @Composable () -> Unit,
) {
    val systemIsDark = isSystemInDarkTheme()

    val isDarkState = when (theme) {
        Theme.System -> systemIsDark
        Theme.Light -> false
        Theme.Dark -> true
    }

    val lang = Locale.current.language
    val currentLangState = remember {
        mutableStateOf(
            when (lang) {
                "en" -> english
                "ru" -> russian
                else -> russian
            }
        )
    }

    val palette = when (isDarkState) {
        true -> darkPalette
        false -> lightPalette
    }

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        LocalDevRushColor provides palette,
        LocalDevRushTypography provides typography,
        LocalLanguage provides currentLangState,
        LocalDevRushStrings provides currentLangState.value
    ) {
        SystemAppearance(!isDarkState)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DevRushTheme.colors.background)
            /*.padding(
                WindowInsets.systemBars.only(WindowInsetsSides.Top).asPaddingValues()
            )*/
        ) {
            content()
        }
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)


val typography = DevRushTypography(
    sfProBold36 = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold30 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold24 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold20 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold18 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold16 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(700)
    ),
    sfPro16 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(400)
    ),
    sfPro20 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight(400)
    ),
    sfPro16Button = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight(590)
    ),
    sfProBoldCaps14 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold14 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(700)
    ),
    sfPro14 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(400)
    ),
    sfPro12 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400)
    ),
    sfPro10 = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight(400)
    ),
    sfProBold10 = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight(700)
    ),
    sfProBold12 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(700)
    ),
    sfProDisplay = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight(500)
    )
)