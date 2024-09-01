package io.eduonline.devrush.devrushlmsmultiplatform.features.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.chat_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.PlayingCat
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.ChatViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.ui.ChatView
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.koinInject

object ChatScreen : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(vectorResource(Res.drawable.chat_icon))
            val title = DevRushTheme.strings.bottomChat

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinInject<ChatViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val onEvent = remember { { it: ChatEvent -> viewModel.obtainEvent(it) } }

        val scope = rememberCoroutineScope()
        val singleImagePicker = rememberImagePickerLauncher(
            selectionMode = SelectionMode.Single,
            scope = scope,
            onResult = { byteArrays ->
                byteArrays.firstOrNull()?.let { onEvent(ChatEvent.ChooseData(it)) }
            }
        )

        PullToRefresh(
            isRefreshing = viewState.isRefresh,
            onRefresh = { viewModel.obtainEvent(viewEvent = ChatEvent.Refresh(refresh = true)) },
            content = {
                when (viewState.fetchChatRequest) {
                    ChatLoadingState.Loading, ChatLoadingState.Idle -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = DevRushTheme.colors.blue1)
                        }
                    }

                    is ChatLoadingState.Error -> {
                        DevRushTopAppBar(title = DevRushTheme.strings.bottomChat, backButton = false,)
                        PlayingCat(DevRushTheme.strings.globalEmptyStateText)
                    }

                    ChatLoadingState.Success -> {
                        ChatView(viewState, onEvent)
                    }
                }
            }
        )

        when (viewAction) {
            null -> {}
            is ChatAction.SendMessage -> TODO()
            ChatAction.ChosePhoto -> {
                singleImagePicker.launch()
                viewModel.clearAction()
            }
        }
    }
}