package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model

sealed interface ChatAction {
    data object ChosePhoto : ChatAction
    class SendMessage(val message: String) : ChatAction
}