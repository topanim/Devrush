package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.file.FileModule
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme

@Composable
fun PaymentsStyledImage(
    cloudKey: String,
) = ElevatedCard(
    modifier = paymentsStyledImageModifier,
    elevation = CardDefaults.elevatedCardElevation(4.dp)
) {
    AsyncImage(
        model = FileModule.getFullFilePath(cloudKey),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun PaymentsStyledImage(
    image: Painter,
) = ElevatedCard(
    modifier = paymentsStyledImageModifier,
    elevation = CardDefaults.elevatedCardElevation(4.dp),
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.elevatedCardColors(DevRushTheme.colors.background)
) {
    Image(
        modifier = Modifier.fillMaxSize().border(1.dp, Color.Black, RoundedCornerShape(10.dp)),
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun PaymentsStyledImage() = ElevatedCard(
    modifier = paymentsStyledImageModifier.clip(RoundedCornerShape(10.dp)),
    colors = CardDefaults.elevatedCardColors(DevRushTheme.colors.baseGreen1),
    elevation = CardDefaults.elevatedCardElevation(4.dp)
) {}

val paymentsStyledImageModifier = Modifier
    .height(75.dp)
    .aspectRatio(16f / 9f)

val gradients = listOf(
    listOf(
        Color(0xfccf58),
        Color(0xfa7f69)
    ),
    listOf(
        Color(0x7784fc),
        Color(0x87f9da)
    ),
    listOf(
        Color(0xcafc58),
        Color(0xfac669)
    )
)