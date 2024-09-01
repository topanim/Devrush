package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.lifecycle.DisposableEffectIgnoringConfiguration
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.baseline_info_24
import devrush_multiplatform.composeapp.generated.resources.baseline_reply_all_24
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages.Attachment
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.SentStatus
import io.eduonline.devrush.devrushlmsmultiplatform.features.leaderboard.ui.views.CustomImage
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompanionMassage(
    replyName: String?,
    reply: String?,
    avatar: String,
    attachments: List<Attachment>,
    name: String,
    message: String,
    time: String,
    status: SentStatus,
    modifier: Modifier,
    response: Boolean,
    response_res: (Boolean) -> Unit
) {
    val currentTheme = LocalTheme.current

    Row(modifier = modifier.fillMaxWidth().padding(end = 42.dp)) {

        if (avatar != "") {
            AsyncImage(
                model = FileModule.getFullFilePath(avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        } else {
            CustomImage(
                name = name,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                textStyle = DevRushTheme.typography.sfProBold14,
                color = DevRushTheme.colors.baseGreen4
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column {
            Text(
                text = name,
                style = DevRushTheme.typography.sfProBold14,
                color = DevRushTheme.colors.c1
            )

            Spacer(modifier = Modifier.size(7.dp))

            Column {
                if (attachments.isNotEmpty()) {
                    attachments.forEach {
                        Column(
                            Modifier.background(
                                if (replyName != null) {//на всякий если у фота будет ответ на другое сообщение
                                    DevRushTheme.colors.baseBlue1
                                } else {
                                    Color.Transparent
                                },
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    bottomEnd = 10.dp,
                                    bottomStart = 10.dp
                                )
                            )
                        ) {
                            if (replyName != null) {//проверяю есть ли ответ на это сообщение
                                VerticalDivider(
                                    modifier = Modifier.fillMaxHeight().width(1.dp),
                                    color = DevRushTheme.colors.baseBlue1
                                )
                                Text(
                                    text = replyName,
                                    style = DevRushTheme.typography.sfPro14,
                                    color = DevRushTheme.colors.baseBlue1,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 8.sp
                                )
                                Text(
                                    text = reply ?: "",
                                    color = DevRushTheme.colors.c6,
                                    fontSize = 10.sp
                                )

                            }

                            AsyncImage(
                                model = FileModule.getFullFilePath(it.cloudKey ?: ""),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(80.dp)
                                    .aspectRatio(16 / 9f)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            if (attachments.last() != it) Gap(4)
                        }
                    }
                }
                when {
                    message != "" -> {
                        Column(
                            Modifier.background(
                                DevRushTheme.colors.c5,
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    bottomEnd = 10.dp,
                                    bottomStart = 10.dp
                                )
                            )
                        ) {
                            if (replyName != null) {// аналогичная проверка на ответ

                                Row(
                                    modifier = Modifier.padding(
                                        start = 10.dp,
                                        end = 15.dp,
                                        top = 5.dp
                                    )
                                ) {
                                    VerticalDivider(
                                        modifier = Modifier.fillMaxHeight().height(22.dp),
                                        color = DevRushTheme.colors.baseBlue1
                                    )
                                    Gap(2)
                                    Column {
                                        Text(
                                            text = replyName,
                                            color = DevRushTheme.colors.baseBlue1,
                                            fontStyle = FontStyle.Italic,
                                            fontSize = 8.sp
                                        )
                                        Text(
                                            text = reply ?: "",
                                            color = DevRushTheme.colors.c6,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 15.dp, vertical = 5.dp),
                                text = message,
                                style = DevRushTheme.typography.sfPro14,
                                color = DevRushTheme.colors.c1
                            )
                        }
                    }
                    (attachments.isEmpty() && message == "") -> {
                        Gap(4)
                        Text(
                            modifier = Modifier
                                .background(
                                    DevRushTheme.colors.c5,
                                    shape = RoundedCornerShape(
                                        topEnd = 10.dp,
                                        bottomEnd = 10.dp,
                                        bottomStart = 10.dp
                                    )
                                )
                                .padding(horizontal = 15.dp, vertical = 10.dp),
                            text = DevRushTheme.strings.chatMessageRemoved,
                            style = DevRushTheme.typography.sfPro14,
                            color = DevRushTheme.colors.c1,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
                TimeOfSent(status = status, time = time, modifier = Modifier.align(Alignment.End))
            }
            if (response) {//открывается кнопка на ответ ( примечание иконка слована
                Column(
                    modifier = Modifier
                        .background(
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
                        Text(text = DevRushTheme.strings.chatAnswer, color = DevRushTheme.colors.c1)
                        Icon(
                            imageVector = vectorResource(Res.drawable.baseline_info_24),//место инфо иконки должен baseline_reply_all_24
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
