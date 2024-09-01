package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.alt_arrow_right
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun TitleRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = DevRushTheme.typography.sfProBold18,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Start,
            color = DevRushTheme.colors.c1
        )
        Row(
            modifier = Modifier
                .clip(AbsoluteRoundedCornerShape(40))
                .background(DevRushTheme.colors.c5)
                .clickable { onClick() }
                .padding(start = 10.dp, end = 4.dp, top = 6.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = DevRushTheme.strings.all,
                style = DevRushTheme.typography.sfPro12,
                textAlign = TextAlign.Center,
                color = DevRushTheme.colors.c1
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = vectorResource(Res.drawable.alt_arrow_right),
                contentDescription = null,
                tint = DevRushTheme.colors.c1,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}