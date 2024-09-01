package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.baseline_info_24
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.Attachment
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.SentStatus
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.CustomImage
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserMassage(
    replyName: String?,
    reply: String?,
    avatar: String,
    attachments: List<Attachment>,
    message: String,
    time: String,
    status: SentStatus,
    modifier: Modifier,
    response: Boolean,
    response_res: (Boolean) -> Unit,
    ) {
    Row(
        modifier = modifier.fillMaxWidth().padding(start = 42.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Column(horizontalAlignment = Alignment.End) {
            if (attachments.isNotEmpty()) {
                Column(
                    Modifier.background(
                         if(replyName!=null) {DevRushTheme.colors.blue1} else { Color.Transparent },
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
                ) {
                    if (replyName != null) {
                        Row(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 15.dp,
                                top = 5.dp
                            )
                        ) {
                            VerticalDivider(
                                modifier = Modifier.fillMaxHeight().height(22.dp),
                                color = DevRushTheme.colors.baseBlue2
                            )
                            Gap(2)
                            Column {
                                Text(
                                    style = DevRushTheme.typography.sfPro14,
                                    text = replyName,
                                    color = DevRushTheme.colors.baseBlue2,
                                    fontSize = 8.sp
                                )
                                Text(
                                    style = DevRushTheme.typography.sfPro14,
                                    text = reply ?: "",
                                    color = DevRushTheme.colors.c6,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                    attachments.forEach {
                        AsyncImage(
                            model = FileModule.getFullFilePath(it.cloudKey ?: ""),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .height(80.dp)
                                .aspectRatio(16 / 9f)
                                .clip(RoundedCornerShape(10.dp))
                        )
                        if (attachments.last() != it) Gap(4)
                    }
                }
            }
            if (attachments.isEmpty() && message != "") {
                Column(
                    Modifier.background(
                        DevRushTheme.colors.blue1,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
                ) {
                    if (replyName != null) {
                        Row(
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 15.dp,
                                top = 5.dp
                            )
                        ) {
                            VerticalDivider(
                                modifier = Modifier.fillMaxHeight().height(22.dp),
                                color = DevRushTheme.colors.baseBlue2
                            )
                            Gap(2)
                            Column {
                                Text(
                                    style = DevRushTheme.typography.sfPro14,
                                    text = replyName,
                                    color = DevRushTheme.colors.baseBlue2,
                                    fontSize = 8.sp
                                )
                                Text(
                                    style = DevRushTheme.typography.sfPro14,
                                    text = reply ?: "",
                                    color = DevRushTheme.colors.c6,
                                    fontSize = 10.sp
                                )
                            }
                        }
                    }
                    Text(
                        modifier = Modifier

                            .padding(vertical = 5.dp, horizontal = 15.dp),
                        text = message,
                        style = DevRushTheme.typography.sfPro14,
                        color = DevRushTheme.colors.c6,
                    )
                }
            } else if (attachments.isEmpty() && message == "") {
                Gap(4)
                Column(
                    Modifier.background(
                        DevRushTheme.colors.blue1,
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
                ) {
                    Text(
                        text = reply ?: "",
                        color = DevRushTheme.colors.c6
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        text = DevRushTheme.strings.chatYouDeletedThisMessage,
                        style = DevRushTheme.typography.sfPro14,
                        color = DevRushTheme.colors.c6,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
            TimeOfSent(
                status = status,
                time = time,
                modifier = Modifier.align(Alignment.End)
            )
            if (response) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.background(
                        DevRushTheme.colors.baseBlue2,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(4.dp)
                            .clickable {
                                response_res(false)
                            }
                    ) {
                        Text(text = "Ответить", color = DevRushTheme.colors.c1)
                        Icon(
                            imageVector = vectorResource(Res.drawable.baseline_info_24),//Внимание должен быть baseline_reply_all_24, но у меня не работает (
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        if (avatar != "") {
            AsyncImage(
                model = FileModule.getFullFilePath(avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            CustomImage(
                name = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                textStyle = DevRushTheme.typography.sfProBold14,
                color = DevRushTheme.colors.baseGreen4
            )
        }
    }
}
