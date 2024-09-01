package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.google_icon
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CustomAuthorizationButton(buttonText: String, onClick: () -> Unit) {
    Button(
        {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DevRushTheme.colors.c6),
        border = BorderStroke(1.dp, color = DevRushTheme.colors.c5),
        shape = RoundedCornerShape(18.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = vectorResource(Res.drawable.google_icon), "",
                Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.padding(end = 10.dp))
            Text(
                buttonText,
                style = DevRushTheme.typography.sfProDisplay,
                color = DevRushTheme.colors.c1
            )
        }
    }
}