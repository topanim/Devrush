package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

enum class Colors(val color: Color) {
    Red(Color(0xFFEF5350)),
    Green(Color(0xFF66BB6A)),
    Blue(Color(0xFF42A5F5)),
    Yellow(Color(0xFFFFEE58)),
    Orange(Color(0xFFFFA726)),
    Purple(Color(0xFFAB47BC)),
    DeepOrange(Color(0xFFFF7043)),
    Brown(Color(0xFF8D6E63)),
    Gray(Color(0xFFBDBDBD)),
    Amber(Color(0xFFFFCA28)),
}

@Composable
fun CustomImage(name: String, modifier: Modifier, textStyle: TextStyle, color: Color) {
    val words = name.split(" ")
    val initials = words.mapNotNull { word ->
        word.firstOrNull()?.toString()
    }.joinToString("")

    Box(
        modifier = modifier.clip(CircleShape).background(color),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = initials.uppercase(),
            style = textStyle,
            color = DevRushTheme.colors.baseWhite
        )
    }
}