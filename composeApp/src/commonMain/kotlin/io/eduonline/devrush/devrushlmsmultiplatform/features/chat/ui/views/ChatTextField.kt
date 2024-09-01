package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.map_arrow_right_icon
import devrush_multiplatform.composeapp.generated.resources.microphone_icon
import devrush_multiplatform.composeapp.generated.resources.paperclip_icon
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import org.jetbrains.compose.resources.vectorResource

@Composable
fun ChatTextField(
    openReply:Boolean,
    name: String?,
    text: String?,
    query: String,
    setQuery: (String) -> Unit,
    onEvent: (ChatEvent) -> Unit,
    replyId: String?=null,
    clouseReplyFun:(Boolean)->Unit

) {
    val focus = LocalFocusManager.current
    val currentTheme = LocalTheme.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c6
                else DevRushTheme.colors.baseWhite
            )
            .padding(horizontal = 20.dp, vertical = 8.dp),

        ) {
        if (openReply) {
            // Заголовок с именем и текстом сообщения
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
                    .background(
                        if (
                            LocalThemeIsDark.current || currentTheme.value == Theme.Dark
                        ) DevRushTheme.colors.c4
                        else DevRushTheme.colors.c5,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 10.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    clouseReplyFun(false)
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = DevRushTheme.colors.blue1,
                        modifier = Modifier.clickable{
                            clouseReplyFun(false)
                        }
                    )
                }
                VerticalDivider(
                    modifier = Modifier.fillMaxHeight(),
                    color = DevRushTheme.colors.blue1
                )
                Spacer(modifier = Modifier.width(2.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = name?:"",
                        color = DevRushTheme.colors.blue1,
                        fontSize = 8.sp
                    )
                    Text(
                        text = text?:"",
                        color = DevRushTheme.colors.c1,
                        fontSize = 10.sp
                    )
                }
            }
        }
        OutlinedTextField(
            value = query,
            onValueChange = setQuery,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                bottomStart = 10.dp,
                bottomEnd = 10.dp,
                topEnd = if (openReply ) 0.dp else 10.dp,
                topStart = if (openReply ) 0.dp else 10.dp
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor =
                if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
                else DevRushTheme.colors.c5,
                unfocusedContainerColor = if (
                    LocalThemeIsDark.current || currentTheme.value == Theme.Dark
                ) DevRushTheme.colors.c4
                else DevRushTheme.colors.c5,

                focusedBorderColor = if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
                else DevRushTheme.colors.c5, //DevRushTheme.colors.background,
                unfocusedBorderColor = if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c4
                else DevRushTheme.colors.c5,

                cursorColor = DevRushTheme.colors.c1,
                focusedTextColor = DevRushTheme.colors.c1,
                unfocusedTextColor = DevRushTheme.colors.c1,
                focusedLeadingIconColor = DevRushTheme.colors.c1,
                unfocusedLeadingIconColor = DevRushTheme.colors.c1,
                focusedTrailingIconColor = DevRushTheme.colors.blue1,
                unfocusedTrailingIconColor = DevRushTheme.colors.blue1
            ),
            leadingIcon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.paperclip_icon),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onEvent(ChatEvent.PaperclipClicked)
                        focus.clearFocus()
                    }
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    Icon(
                        imageVector = vectorResource(Res.drawable.map_arrow_right_icon),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onEvent(ChatEvent.SendMessage(query,if (openReply) replyId else{null}   ))
                            setQuery("")
                            focus.clearFocus()
                            clouseReplyFun(false)
                        }
                    )
                } else {
                    Icon(
                        imageVector = vectorResource(Res.drawable.microphone_icon),
                        contentDescription = null,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions {
                focus.clearFocus()
            },
        )
    }
}