package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun AccessesPaymentsLoadingView() = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxSize()
) { CircularProgressIndicator(color = DevRushTheme.colors.blue1) }