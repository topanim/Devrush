import androidx.compose.ui.window.ComposeUIViewController
import io.eduonline.devrush.devrushlmsmultiplatform.DevRushLMSApp
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { DevRushLMSApp() }
