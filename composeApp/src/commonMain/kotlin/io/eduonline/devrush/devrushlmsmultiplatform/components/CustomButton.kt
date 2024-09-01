package io.eduonline.devrush.devrushlmsmultiplatform.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun CustomButton(
    buttonText: String,
    enable: Boolean = true,
    progressBar: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        shape = RoundedCornerShape(100.dp),
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            containerColor = DevRushTheme.colors.blue1,
            disabledContainerColor = DevRushTheme.colors.blue1,
            disabledContentColor = DevRushTheme.colors.c6
        )
    ) {
        if (!enable) {
            progressBar()
        } else {
            Text(
                buttonText,
                style = DevRushTheme.typography.sfPro16Button,
                color = DevRushTheme.colors.c6
            )
        }
    }
}


