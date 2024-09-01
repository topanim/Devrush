package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme


@Composable
fun LoadingShimmer() = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.fillMaxWidth()
) { CircularProgressIndicator(color = DevRushTheme.colors.blue1) }