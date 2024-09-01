package io.eduonline.devrush.devrushlmsmultiplatform.features.profileSettings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.arrow_right
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

data class RadioOption(
    val title: String,
    val value: Theme,
)

@Composable
fun ChooseThemeField(
    title: String,
    initial: RadioOption,
    options: List<RadioOption>,
    onChange: (Theme) -> Unit,
) {
    val showDialog = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(40.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                showDialog.value = true
            }
    ) {
        Text(
            title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c1
        )

        Icon(
            modifier = Modifier.padding(end = 4.dp),
            imageVector = vectorResource(Res.drawable.arrow_right),
            tint = DevRushTheme.colors.c2,
            contentDescription = null
        )
    }

    if (showDialog.value) {
        ChooseThemeDialog(
            initial = initial,
            options = options,
            onDismissRequest = {
                showDialog.value = false
            }
        ) {
            onChange(it)
            showDialog.value = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseThemeDialog(
    initial: RadioOption,
    options: List<RadioOption>,
    onDismissRequest: () -> Unit,
    onConfirm: (Theme) -> Unit,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(initial) }

    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(DevRushTheme.colors.background)
    ) {
        Column(Modifier.selectableGroup()) {
            options.forEach { radioOption ->
                Row(
                    Modifier.fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (radioOption == selectedOption),
                            onClick = {
                                onOptionSelected(radioOption)
                                onConfirm(radioOption.value)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (radioOption == selectedOption),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = DevRushTheme.colors.blue1,
                        ),
                        onClick = null // null recommended for accessibility with screenreaders
                    )
                    Text(
                        text = radioOption.title,
                        style = DevRushTheme.typography.sfPro14,
                        color = DevRushTheme.colors.c2,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}
