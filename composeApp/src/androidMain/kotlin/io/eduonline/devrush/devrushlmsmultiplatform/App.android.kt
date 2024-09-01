package io.eduonline.devrush.devrushlmsmultiplatform


import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.eduonline.devrush.devrushlmsmultiplatform.di.initKoin
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.ChatViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.chat.presentation.model.ChatEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin

class AndroidApp : Application() {

    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initKoin {
            androidContext(this@AndroidApp)
        }
    }


}

class AppActivity : ComponentActivity() {

    private val koin: Koin = getKoin()
    private val chatViewModel by koin.inject<ChatViewModel>()
    private val tokenRepository by koin.inject<AuthTokenRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DevRushLMSApp()
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(IO).launch {
            tokenRepository.getTokensOrNull() ?: return@launch
            tokenRepository.refreshTokens()
            withContext(Main) {
                chatViewModel.obtainEvent(ChatEvent.ForceConnect)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        chatViewModel.obtainEvent(ChatEvent.CloseConnection)
    }
}
