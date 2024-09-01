package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap

@Composable
fun RatingBar(
    emptyIcon: ImageVector,
    filledIcon: ImageVector,
    rating: Double = 0.0,
    stars: Int = 5,
    gap: Int = 5,
    editable: Boolean = false,
    iconSize: Dp = 20.dp,
    starsColor: Color = Color.Yellow,
    modifier: Modifier = Modifier,
    onRatingChanged: (Double) -> Unit = {},
) {
    Row {
        for (index in 1..stars) {
            Icon(
                imageVector = if (index <= rating) filledIcon else emptyIcon,
                contentDescription = null,
                tint = starsColor,
                modifier = (
                        if (editable) modifier.clickable { onRatingChanged(index.toDouble()) }
                        else modifier
                        ).size(iconSize)
            )
            Gap(gap)
        }
    }
}



