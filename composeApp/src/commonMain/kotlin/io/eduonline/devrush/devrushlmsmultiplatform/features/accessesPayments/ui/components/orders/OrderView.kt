package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.order_img
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.PaymentsStyledImage
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToMillis
import io.eduonline.devrush.devrushlmsmultiplatform.utils.simpleFormatTime
import org.jetbrains.compose.resources.painterResource

@Composable
fun OrderView(
    number: Int,
    amount: Float,
    creationDate: String,
    onClick: () -> Unit,
) = Row(
    Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(DevRushTheme.colors.c6)
        .padding(10.dp)
) {
    PaymentsStyledImage(image = painterResource(Res.drawable.order_img))

    Gap(10)

    Column {
        Text(
            "${DevRushTheme.strings.profileOrder} $number",
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        Text(
            "${DevRushTheme.strings.profileOrderAmount} $amount", //TODO: add currency
            style = DevRushTheme.typography.sfPro12,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        val dateOfCreation = derivedStateOf {
            simpleFormatTime(parseStringToMillis(creationDate))
        }

        Text(
            "${DevRushTheme.strings.profileCreationDate} - ${dateOfCreation.value}", //TODO: add formating
            style = DevRushTheme.typography.sfPro10,
            color = DevRushTheme.colors.c2
        )

        Gap(4)

        /* SubscriptionActionButton(
            text = DevRushTheme.strings.profilePayOrder,
            enabled = false,
            textColor = DevRushTheme.colors.c5,
            color = DevRushTheme.colors.green2,
            onClick = onClick
        ) */
    }

}