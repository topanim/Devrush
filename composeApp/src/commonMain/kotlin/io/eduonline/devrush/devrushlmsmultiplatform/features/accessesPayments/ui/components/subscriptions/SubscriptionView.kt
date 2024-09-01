package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.subscriptions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.updateSubscription.SubscriptionState
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.SubscriptionsDataItem
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.presentation.models.AccessesPaymentsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.PaymentsStyledImage
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.SubscriptionActionButton
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToMillis
import io.eduonline.devrush.devrushlmsmultiplatform.utils.simpleFormatTime

@Composable
fun SubscriptionView(
    data: SubscriptionsDataItem,
    onEvent: (AccessesPaymentsEvent) -> Unit,
) = Row(
    Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(DevRushTheme.colors.c6)
        .height(IntrinsicSize.Min)
        .padding(10.dp)
) {
    if (data.imageCloudKey != null) PaymentsStyledImage(data.imageCloudKey)
    else PaymentsStyledImage()

    Gap(10)

    Column {
        Text(
            data.title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        Text(
            "${DevRushTheme.strings.profileOrderAmount} ${data.amount}", //TODO: add currency
            style = DevRushTheme.typography.sfPro12,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        val payDate = derivedStateOf {
            simpleFormatTime(parseStringToMillis(data.endDate))
        }

        Text(
            when (data.renewalState) {
                SubscriptionState.Active -> "${DevRushTheme.strings.profileDateOfDebiting} ${payDate.value}" //TODO: add formating
                SubscriptionState.Paused -> DevRushTheme.strings.profileRenewalIsSuspended
                else -> ""
            },
            style = DevRushTheme.typography.sfPro10,
            color = DevRushTheme.colors.c2
        )

        Gap(4)

        val text: String
        val color: Color
        val textColor: Color

        if (data.active) when (data.renewalState) {
            SubscriptionState.Active -> {
                text = DevRushTheme.strings.profileCancelSubscriptionRenewal
                color = DevRushTheme.colors.baseRed
                textColor = DevRushTheme.colors.c5
            }

            SubscriptionState.Paused -> {
                text = DevRushTheme.strings.profileRenewSubscription
                color = DevRushTheme.colors.baseGreen1
                textColor = DevRushTheme.colors.c5
            }

            else -> {
                text = ""
                color = DevRushTheme.colors.c2
                textColor = DevRushTheme.colors.c5
            }
        } else {
            text = DevRushTheme.strings.profilePayOrder
            color = DevRushTheme.colors.c2
            textColor = DevRushTheme.colors.c5
        }

        SubscriptionActionButton(
            text = text,
            enabled = true,
            color = color,
            textColor = textColor,
            onClick = {
                if (data.active) when (data.renewalState) {
                    SubscriptionState.Active -> onEvent(
                        AccessesPaymentsEvent.CancelSubscription(data.id)
                    )

                    SubscriptionState.Paused -> onEvent(
                        AccessesPaymentsEvent.RenewSubscription(data.id)
                    )

                    else -> {}
                } else onEvent(AccessesPaymentsEvent.PaySubscription(data.id))

            }
        )
    }
}