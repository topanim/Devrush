package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlin.math.roundToInt

@Composable
fun ProgressBar(
    progress: Float,
    showProgress: Boolean = true,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center,
    modifier = modifier.fillMaxWidth()
) {
    LinearProgressIndicator(
        progress = { progress / 100f },
        strokeCap = StrokeCap.Round,
        color = DevRushTheme.colors.blue1,
        trackColor = DevRushTheme.colors.c5,
        modifier = Modifier.weight(1f)
    )

    if (showProgress) {
        Gap(10)

        Text(
            text = "${progress.roundToInt()}%",
            style = DevRushTheme.typography.sfProBold14,
            color = DevRushTheme.colors.c1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(4.dp, 0.dp)
        )
    }
}
