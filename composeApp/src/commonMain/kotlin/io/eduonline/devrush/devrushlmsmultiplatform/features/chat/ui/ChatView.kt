package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.round_double_arrow_down
import io.eduonline.devrush.devrushlmsmultiplatform.LocalTheme
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.theme.Theme
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.SentStatus
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views.AnimatedTypingIndicator
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views.ChatTextField
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views.CompanionMassage
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views.DateTitle
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.views.UserMassage
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.utils.getMonthName
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.utils.getParsedDate
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.CustomImage
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.LocalThemeIsDark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatView(state: ChatViewState, onEvent: (ChatEvent) -> Unit) {


    var id by remember { mutableStateOf(0) }//id отведа
    var isBlurBoolean by remember { mutableStateOf(false) }//проверка назамыливание
    var openReplyFun by remember { mutableStateOf(false) }//открытие ответа на текст филд

    val query = remember { mutableStateOf("") }
    val focus = LocalFocusManager.current
    val currentTheme = LocalTheme.current
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val isAtBottom = remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lazyListState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(state.chatItems.size, state.printersId) {
        scope.launch {
            if (state.chatItems.isEmpty()) return@launch
            lazyListState.scrollToItem(state.chatItems.size - 1)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DevRushTopAppBar(DevRushTheme.strings.bottomChat, backButton = false)

        Box(
            modifier = Modifier.fillMaxWidth().weight(1f).shadow(
                elevation = 8.dp, shape = AbsoluteRoundedCornerShape(24.dp, 24.dp)
            ).background(
                if (LocalThemeIsDark.current || currentTheme.value == Theme.Dark) DevRushTheme.colors.c6
                else DevRushTheme.colors.baseWhite
            )
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 23.dp)
                    .pointerInput(query.value) {
                        detectTapGestures(onTap = {
                            focus.clearFocus()
                        })
                    },
                state = lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item { Spacer(modifier = Modifier.size(20.dp)) }

                if (state.chatItems.isEmpty()) return@LazyColumn
                itemsIndexed(state.chatItems) { index, message ->
                    /** Парсинг даты */
                    val parsedDate = getParsedDate(message.createdDate)

                    /** Получаем нужный формат */
                    val date = "${parsedDate.dayOfMonth} ${getMonthName(parsedDate.month.name)}"

                    /** Парсинг даты следующего дня */
                    val parsePreviousDay =
                        if (index > 0) getParsedDate(state.chatItems[index - 1].createdDate)
                        else parsedDate

                    /** Получаем нужный формат */
                    val previousDay =
                        "${parsePreviousDay.dayOfMonth} ${getMonthName(parsePreviousDay.month.name)}"

                    /** Парсинг времени сообщения */
                    val time =
                        if (parsedDate.minute < 10) "${parsedDate.hour}:0${parsedDate.minute}"
                        else "${parsedDate.hour}:${parsedDate.minute}"

                    /** Проверяем является ли дата новой */
                    if (index == 0 || index > 0 && date != previousDay) {
                        DateTitle(date)
                        Spacer(modifier = Modifier.size(20.dp))
                    }
                    Column(
                        Modifier
                            .combinedClickable(//отслеживаю клик и зажатие на сообщение
                                onClick = {
                                    if (isBlurBoolean == true) {//выйдя из размыливание кликнув на любое сообщение
                                        isBlurBoolean = false
                                    }
                                },
                                onLongClick = {
                                    if (isBlurBoolean == false) {//размыливание вокруг зажатого сообщения и выевление кнопки на ответ
                                        isBlurBoolean =
                                            true
                                    }
                                    id = index//обнавляю что за id сообщения
                                },
                            )
                    ) {
                        if (message.authorId == state.currentUserInfo?.id) {
                            val coroutineScope = rememberCoroutineScope()
                            val pageCount = 2
                            val pagerState = rememberPagerState(
                                initialPage = 0,
                                initialPageOffsetFraction = 0f
                            ) {
                                pageCount
                            }

                            if (pagerState.currentPage != 0) {//отслеживается свйпнул ли сообщение
                                LaunchedEffect(pagerState.currentPage) {
                                    while (pagerState.currentPage != 0) {//вернуть в исходное положение до тех пор пока не вернется в первоначальное положение
                                        delay(200)
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(0)
                                        }
                                    }
                                }
                                id = index //обновляю индекс сообщения
                                openReplyFun = true //открываю ответ в текст филд

                            }
                            HorizontalPager(//то что как раз делает свайп
                                state = pagerState,
                                modifier = Modifier.fillMaxWidth()
                            ) { page ->
                                if (page == 0) {
                                    Column {
                                        UserMassage(
                                            replyName = state.chatMembers.find{// имя ответа сообщения
                                                it.id == state.chatItems[index].reply?.authorId
                                            }?.firstName,
                                            reply = state.chatItems[index].reply?.text ?: "",//текст ответа сообщения
                                            avatar = state.currentUserInfo.avatar?.cloudKey ?: "",
                                            attachments = message.attachments ?: emptyList(),
                                            message = message.text ?: "",
                                            time = time,
                                            status = if (message.index > state.lastSeenIndex) SentStatus.IS_SENT else SentStatus.IS_READ,
                                            modifier = Modifier.blur(if (isBlurBoolean && index != id) 5.dp else 0.dp), //проверяю это сообщения является ли зажатым или нет
                                            response = (isBlurBoolean && index == id),// проверка тот сообщения который зажатый
                                            response_res = {
                                                isBlurBoolean = it//сброс замытия
                                                id = index//на всякий еще раз обновим
                                                openReplyFun = true //открываю ответ в текст филд
                                            }
                                        )
                                    }
                                } else {
                                    Box(Modifier.size(10.dp))

                                }
                            }
                        } else {
                            val user = state.chatMembers.find {
                                it.id == message.authorId && it.id != state.currentUserInfo?.id
                            }
                            val coroutineScope = rememberCoroutineScope()
                            val pageCount = 2
                            val pagerState = rememberPagerState(
                                initialPage = 0,
                                initialPageOffsetFraction = 0f
                            ) {
                                pageCount
                            }

                            if (pagerState.currentPage != 0) {//отслеживается свйпнул ли сообщение
                                LaunchedEffect(pagerState.currentPage) {
                                    while (pagerState.currentPage != 0) {//вернуть в исходное положение до тех пор пока не вернется в первоначальное положение
                                        delay(200)
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(0)
                                        }
                                    }
                                }
                                id = index //обновляю индекс сообщения
                                openReplyFun = true //открываю ответ в текст филд
                            }

                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxWidth()
                            ) { page ->
                                if (page == 0) {
                                    CompanionMassage(
                                        replyName = state.chatMembers.find{// имя ответа сообщения
                                            it.id == state.chatItems[index].reply?.authorId
                                        }?.firstName,
                                        reply = state.chatItems[index].reply?.text ?: "",//текст ответа сообщения
                                        avatar = user?.avatar?.cloudKey ?: "",
                                        attachments = message.attachments ?: emptyList(),
                                        name = user?.firstName + " " + user?.lastName,
                                        message = message.text ?: "",
                                        time = time,
                                        status = SentStatus.IS_READ,
                                        modifier = Modifier.blur(if (isBlurBoolean && index != id) 5.dp else 0.dp),//проверяю это сообщения является ли зажатым или нет
                                        response = (isBlurBoolean && index == id),// проверка тот сообщения который зажатый
                                        response_res = {
                                            isBlurBoolean = it    //сброс замытия
                                            id = index            //на всякийеще раз обновим
                                            openReplyFun = true   //открываю ответ в текст филд
                                        }

                                    )
                                } else {
                                    Box(Modifier.size(10.dp))

                                }
                            }
                        }
                    }
                }
                // Анимация
                if (state.printersId != null) {
                    item {
                        state.chatMembers.find {
                            it.id == state.printersId
                        }.also { co.touchlab.kermit.Logger.d("UserFind") { it.toString() } }
                            ?.let {

                                Row(modifier = Modifier.fillMaxWidth().padding(end = 42.dp)) {
                                    if ((it.avatar?.cloudKey ?: "") != "") {
                                        AsyncImage(
                                            model = FileModule.getFullFilePath(
                                                it.avatar?.cloudKey ?: ""
                                            ),
                                            contentDescription = null,
                                            modifier = Modifier.size(40.dp).clip(CircleShape)
                                        )
                                    } else {
                                        CustomImage(
                                            name = it.firstName + " " + it.lastName,
                                            modifier = Modifier.size(40.dp).clip(CircleShape),
                                            textStyle = DevRushTheme.typography.sfProBold14,
                                            color = DevRushTheme.colors.baseGreen4
                                        )
                                    }
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Column {
                                        Text(
                                            text = it.firstName + it.lastName,
                                            style = DevRushTheme.typography.sfProBold14,
                                            color = DevRushTheme.colors.c1
                                        )
                                        Spacer(modifier = Modifier.size(7.dp))
                                        Box(
                                            modifier = Modifier.background(
                                                DevRushTheme.colors.c5,
                                                shape = RoundedCornerShape(
                                                    topEnd = 10.dp,
                                                    bottomEnd = 10.dp,
                                                    bottomStart = 10.dp
                                                )
                                            ).padding(horizontal = 15.dp, vertical = 10.dp)
                                        ) {
                                            AnimatedTypingIndicator()
                                        }
                                    }
                                }

                            }
                    }
                }
            }
            if (!isAtBottom.value) {
                Icon(
                    modifier = Modifier.align(Alignment.BottomEnd)
                        .padding(end = 10.dp, bottom = 10.dp).size(30.dp).clickable {
                            scope.launch {
                                if (state.chatItems.isEmpty()) return@launch
                                lazyListState.scrollToItem(state.chatItems.size - 1)
                            }
                        },
                    imageVector = vectorResource(Res.drawable.round_double_arrow_down),
                    contentDescription = null,
                    tint = DevRushTheme.colors.blue1
                )
            }
        }
        state.chatMembers.find { it.id == state.chatItems[id].authorId }?.let {
            (if (state.chatItems[id].attachments?.isNotEmpty() == true) "Фото" else state.chatItems[id].text)?.let { it1 ->
                ChatTextField(
                    openReply=openReplyFun,//проверка на открытие ответа,
                    name = (it.firstName + " " + it.lastName) ?: "",//имя от ответа сообщения
                    text = it1 ?: "",//текст ответа сообщения
                    query = query.value,
                    setQuery = { query.value = it },
                    onEvent = onEvent,
                    replyId = state.chatItems[id].id, //id сообщения ответа
                    clouseReplyFun = {openReplyFun=it}// обновление проверки на открытие сообщении
                )
            }
        }
    }
}
