package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.settings
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ProfileTopBar(
    state: ProfileState,
    navigator: Navigator,
    focusManager: FocusManager,
    screen: Screen,
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(DevRushTheme.colors.background).height(70.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = DevRushTheme.strings.profile,
            style = DevRushTheme.typography.sfProBold18,
            color = DevRushTheme.colors.c1
        )
        Row(modifier = Modifier.align(Alignment.CenterEnd).padding(end = 26.dp)) {
            Icon(
                imageVector = vectorResource(Res.drawable.settings),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        navigator.push(screen)
                    },
            )
        }
    }


}