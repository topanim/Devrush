package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun DevRushTopAppBar(
    title: String,
    backButton: Boolean = true,
    style: TextStyle = DevRushTheme.typography.sfProBold16,
    alignment: Alignment = Alignment.Center,
    modifier: Modifier = Modifier,
    clipText: Boolean = true,
    addDivider: Boolean = false,
    padding: Int = 20,
    trailing: @Composable BoxScope.(Modifier) -> Unit = {},
    onBackClick: () -> Unit = {}

) = Column(
    Modifier
        .fillMaxWidth()
        .background(DevRushTheme.colors.background)
        .then(modifier)
) {
    Box(
        Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(horizontal = padding.dp)
    ) {
        if (backButton) Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = DevRushTheme.colors.c1,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onBackClick() }
                .padding(8.dp)
                .align(Alignment.CenterStart)
        )

        Text(
            title,
            style = style.copy(
                textAlign = when (alignment) {
                    Alignment.TopStart -> TextAlign.Start
                    Alignment.TopCenter -> TextAlign.Center
                    Alignment.TopEnd -> TextAlign.End
                    Alignment.BottomStart -> TextAlign.Start
                    Alignment.BottomCenter -> TextAlign.Center
                    Alignment.BottomEnd -> TextAlign.End
                    Alignment.CenterStart -> TextAlign.Start
                    Alignment.Center -> TextAlign.Center
                    Alignment.CenterEnd -> TextAlign.End
                    else -> TextAlign.Center
                }
            ),
            color = DevRushTheme.colors.c1,
            modifier = Modifier
                .align(alignment)
                .fillMaxWidth()
                .padding(horizontal = if (clipText) 60.dp else 0.dp)
        )

        trailing(Modifier.align(Alignment.CenterEnd))
    }

    if (addDivider) HorizontalDivider(color = DevRushTheme.colors.c4)
}