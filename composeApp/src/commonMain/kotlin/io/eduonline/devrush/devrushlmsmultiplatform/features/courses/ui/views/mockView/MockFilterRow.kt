package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.mockView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.shimmerEffect

@Composable
fun MockFilterRow() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(6) {
            MockItemFilterCell(
                modifier = Modifier
                    .padding(start = if (it == 0) 20.dp else 0.dp)
                    .padding(end = if (it == 6) 20.dp else 0.dp)
            )
        }
    }
}

@Composable
fun MockItemFilterCell(modifier: Modifier) {
    Box(
        modifier = modifier.padding(top = 8.dp, bottom = 4.dp).size(55.dp, 36.dp)
            .clip(RoundedCornerShape(20.dp))
            .shimmerEffect(),
    )
}