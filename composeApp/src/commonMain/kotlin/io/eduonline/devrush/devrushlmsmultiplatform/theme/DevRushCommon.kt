package io.eduonline.devrush.devrushlmsmultiplatform.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class DevRushColors(
    val background: Color,
    val baseWhite: Color = Color(0xFFFFFFFF),
    val baseBlue1: Color = Color(0xFF3B4560),
    val baseBlue2: Color = Color(0xFFB7CEDD),
    val baseGreen1: Color = Color(0xFFB5CB5C),
    val baseGreen2: Color = Color(0xFF778567),
    val baseGreen3: Color = Color(0xFF9AAE4F),
    val baseGreen4: Color = Color(0xFFDBE5AC),
    val baseGreen5: Color = Color(0xFFF8F5CC),
    val basePink1: Color = Color(0xFFBA768F),
    val basePink2: Color = Color(0xFFDBB0C1),
    val basePurple1: Color = Color(0xFF4E355A),
    val basePurple2: Color = Color(0xFF8E74A8),
    val basePurple3: Color = Color(0xFFD0C5E0),
    val baseRed: Color = Color(0xFFEF4444),
    val red1: Color = Color(0xFFDF7070),
    val green2: Color = Color(0xFFB5DF70),
    val baseDarkBlue: Color = Color(0xFF1C274C),
    val newGrey: Color = Color(0xFF69788D),
    val isMeColor: Color = Color(0xFF98ACD5),
    val newDark: Color,
    val blue1: Color,
    val blue2: Color,
    val c1: Color,
    val c2: Color,
    val c3: Color,
    val c4: Color,
    val c5: Color,
    val c6: Color,
)

val darkPalette = DevRushColors(
    background = Color(0xFF151515),
    c1 = Color(0xFFF7F7F7),
    c2 = Color(0xFFC8C8C8),
    c3 = Color(0xFF37373A),
    c4 = Color(0xFF242424),
    c5 = Color(0xFF151515),
    c6 = Color(0xFF0E0E0E),
    blue1 = Color(0xFF98C3F6),
    blue2 = Color(0xFF887FE4),
    newDark = Color(0xFF39394B)
)

val lightPalette = DevRushColors(
    background = Color(0xFFFFFFFF),
    c1 = Color(0xFF3C3D3B),
    c2 = Color(0xFF7C7C7B),
    c3 = Color(0xFFB2B2B2),
    c4 = Color(0xFFD0D0D0),
    c5 = Color(0xFFEDEDED),
    c6 = Color(0xFFFBFBFB),
    blue1 = Color(0xFF5F49F3),
    blue2 = Color(0xFF07338C),
    newDark = Color(0xFF060C26)

)

data class DevRushTypography(
    val sfProBold36: TextStyle,
    val sfProBold30: TextStyle,
    val sfProBold24: TextStyle,
    val sfProBold20: TextStyle,
    val sfProBold18: TextStyle,
    val sfProBold16: TextStyle,
    val sfPro16: TextStyle,
    val sfPro14: TextStyle,
    val sfPro20: TextStyle,
    val sfPro12: TextStyle,
    val sfPro10: TextStyle,
    val sfProBoldCaps14: TextStyle,
    val sfProBold14: TextStyle,
    val sfProBold10: TextStyle,
    val sfProBold12: TextStyle,
    val sfProDisplay: TextStyle,
    val sfPro16Button: TextStyle,
)


object DevRushTheme {
    internal val colors: DevRushColors
        @Composable
        get() = LocalDevRushColor.current

    internal val typography: DevRushTypography
        @Composable
        get() = LocalDevRushTypography.current

    internal val strings: Strings
        @Composable
        get() = LocalDevRushStrings.current
}

internal val LocalDevRushColor = staticCompositionLocalOf<DevRushColors> { lightPalette }

internal val LocalDevRushTypography =
    staticCompositionLocalOf<DevRushTypography> { error("No font provided") }

internal val LocalDevRushStrings = staticCompositionLocalOf { english }