package io.eduonline.devrush.devrushlmsmultiplatform.features.registration.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun TextBlock(onEvent:() -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = DevRushTheme.strings.registrationTitleAuthorization,
            color = DevRushTheme.colors.c2,
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
        )
        Text(
            modifier = Modifier.clickable { onEvent() },
            text = DevRushTheme.strings.registrationEnterAuthorization,
            color = DevRushTheme.colors.blue1,
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
        )
    }
}