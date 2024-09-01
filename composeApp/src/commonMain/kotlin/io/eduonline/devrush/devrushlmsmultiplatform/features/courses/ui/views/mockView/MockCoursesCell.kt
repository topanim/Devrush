package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views.mockView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun MockCoursesCell(modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DevRushTheme.colors.c6)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxHeight().aspectRatio(16f / 9f).clip(
                RoundedCornerShape(10.dp)
            ).then(
                modifier
            )
        )

        Spacer(modifier = Modifier.size(10.dp))

        Column {


            Row(
                modifier = Modifier.fillMaxWidth().height(12.dp).clip(RoundedCornerShape(4.dp))
                    .then(modifier)
            ) {}
            Gap(5)
            repeat(3) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.6f).height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .then(modifier)
                ) {}
                Gap(3)
            }
        }
    }
}