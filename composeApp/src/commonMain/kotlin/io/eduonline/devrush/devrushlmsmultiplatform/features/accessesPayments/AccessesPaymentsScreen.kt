package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.eduonline.devrush.devrushlmsmultiplatform.components.DevRushTopAppBar
import io.eduonline.devrush.devrushlmsmultiplatform.components.PullToRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.AccessesPaymentsViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.AccessesPaymentsView
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.SubscriptionPaymentModalSheet
import io.eduonline.devrush.devrushlmsmultiplatform.navigation.AppScreensProviders
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import org.koin.compose.koinInject

class AccessesPaymentsScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinInject<AccessesPaymentsViewModel>()
        val viewState by viewModel.viewStates().collectAsState()
        val viewAction by viewModel.viewActions().collectAsState(null)
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        val onEvent: (AccessesPaymentsEvent) -> Unit = remember { { viewModel.obtainEvent(it) } }

        LaunchedEffect(Unit) {
            onEvent(AccessesPaymentsEvent.GetGlobal(false))
        }

        PullToRefresh(
            content = {

                Scaffold(
                    containerColor = DevRushTheme.colors.background,
                    contentWindowInsets = WindowInsets
                        .systemBars
                        .only(WindowInsetsSides.Bottom),
                    topBar = {
                        DevRushTopAppBar(
                            DevRushTheme.strings.profileAccessPayments,
                            addDivider = true,
                            modifier = Modifier.padding(
                                WindowInsets
                                    .systemBars
                                    .only(WindowInsetsSides.Top)
                                    .asPaddingValues()
                            )
                        ) { navigator.pop() }
                    }
                ) {
                    if (viewState.showPaymentSheet)
                        SubscriptionPaymentModalSheet(
                            viewState.paymentState?.paymentUrl ?: "",
                            sheetState,
                            onEvent
                        )

                    AccessesPaymentsView(Modifier.padding(it), viewState, onEvent)
                }

                when (viewAction) {
                    null -> {}
                    is AccessesPaymentsAction.OpenCourse -> {
                        val data = viewAction as AccessesPaymentsAction.OpenCourse
                        val screen = rememberScreen(
                            AppScreensProviders.DetailCourse(
                                data.id, "", data.imageCloudKey
                            )
                        )
                        viewModel.clearAction()
                        navigator.push(screen)
                    }

                    is AccessesPaymentsAction.OpenLibrary -> {
                        val data = viewAction as AccessesPaymentsAction.OpenLibrary
                        val screen = rememberScreen(
                            AppScreensProviders.DetailCourse(
                                data.id, null, data.imageCloudKey
                            )
                        )
                        viewModel.clearAction()
                        navigator.push(screen)
                    }
                }
            }, isRefreshing = viewState.isRefresh,
            onRefresh = {
                viewModel.obtainEvent(AccessesPaymentsEvent.Refresh(true))
            }
        )
    }
}