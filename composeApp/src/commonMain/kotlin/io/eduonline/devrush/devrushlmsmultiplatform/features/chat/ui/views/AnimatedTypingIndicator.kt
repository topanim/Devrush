package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.DevRushLMSApp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlin.math.abs

@Composable
fun AnimatedTypingIndicator(
    modifier: Modifier = Modifier,
    circleStartSize: Dp = 8.dp,
    circleTargetSize: Dp = 9.dp,
    activeColor: Color = DevRushTheme.colors.blue1,
    inactiveColor: Color = DevRushTheme.colors.baseWhite,
    spaceBetweenCircles: Dp = 6.dp,
    totalDots: Int = 3,
) {
    val infiniteTransition = rememberInfiniteTransition(
        label = "Typing indicator animation"
    )
    /** Progress переменная, которая плавно изменяется от 0 до 1 в течение 1 секунды.
     *  Это значение представляет текущий прогресс анимации и используется для изменения состояния каждого кружка.
     */
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Typing indicator progress animation"
    )
    //Высота и ширина анимации, зависит о кружков
    val defaultWidth = remember {
        circleTargetSize * totalDots + spaceBetweenCircles * (totalDots + 1)
    }
    val defaultHeight = remember { circleTargetSize * 2 }

    Canvas(modifier = modifier
        .width(defaultWidth)
        .height(defaultHeight))
    //Создается холст
    {
        val startRadiusPX = circleStartSize.toPx() / 2f
        val targetRadiusPX = circleTargetSize.toPx() / 2f
        val dotArea = 1f / totalDots
        //Просто последовательно по индексу увеличиваем кружки
        repeat(totalDots) { index ->
            val dotCenterPos = (index + 0.5f) * dotArea
            val proximity = (1 - abs(progress - dotCenterPos) / dotArea).coerceIn(0f, 1f)
            drawCircle(
                color = lerp(inactiveColor, activeColor, proximity),
                radius = startRadiusPX + (targetRadiusPX - startRadiusPX) * proximity,
                center = Offset(
                    x = size.width / totalDots * index + size.width / (2 * totalDots),
                    y = center.y
                )
            )
        }
    }
}