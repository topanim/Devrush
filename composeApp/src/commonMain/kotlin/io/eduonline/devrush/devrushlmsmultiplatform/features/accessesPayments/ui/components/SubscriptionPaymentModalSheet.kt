package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscriptionPaymentModalSheet(
    paymentUrl: String,
    sheetState: SheetState,
    onEvent: (AccessesPaymentsEvent) -> Unit,
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        shape = BottomSheetDefaults.HiddenShape,
        containerColor = DevRushTheme.colors.background,
        contentColor = DevRushTheme.colors.baseWhite,
        onDismissRequest = { onEvent(AccessesPaymentsEvent.ClosePaymentSheet) }
    ) {
        val webViewState = rememberWebViewState(paymentUrl)
        webViewState.webSettings.apply {
            isJavaScriptEnabled = true
            allowFileAccessFromFileURLs = true
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(10.dp)
                .animateContentSize()
        ) {
            WebView(webViewState)
        }
    }
}