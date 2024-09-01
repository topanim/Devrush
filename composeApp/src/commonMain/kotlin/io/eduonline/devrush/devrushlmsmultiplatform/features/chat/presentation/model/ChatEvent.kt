package io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model

sealed interface ChatEvent {
    data object InitConnection : ChatEvent
    data object ForceConnect : ChatEvent
    data object CloseConnection : ChatEvent
    data object GetMessages : ChatEvent
    data object PaperclipClicked : ChatEvent
    class ChooseData(val data: ByteArray) : ChatEvent
    class SendMessage(val message: String, val replyID:String?) : ChatEvent
    class Refresh(val refresh: Boolean) : ChatEvent



}