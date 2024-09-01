package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.accesses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.AccessesDataItemType
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.ui.components.PaymentsStyledImage
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToMillis
import io.eduonline.devrush.devrushlmsmultiplatform.utils.simpleFormatTime

@Composable
fun AccessView(
    id: String,
    title: String,
    banner: String?,
    type: AccessesDataItemType,
    rate: String?,
    startDate: String,
    endDate: String?,
    onClick: () -> Unit
) = Row(
    Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(DevRushTheme.colors.c6)
        .padding(10.dp)
        .clickable(onClick = onClick)
) {
    val formattedStartDate = derivedStateOf {
        simpleFormatTime(parseStringToMillis(startDate))
    }

    val formattedEndDate = derivedStateOf {
        if (endDate == null) null
        else simpleFormatTime(parseStringToMillis(endDate))
    }

    if (banner != null) PaymentsStyledImage(banner)
    else PaymentsStyledImage()

    Gap(10)

    Column {
        Text(
            title,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        Text(
            if (type == AccessesDataItemType.Course) "${DevRushTheme.strings.profileCourse} ${
                if (rate != null) "- $rate" else ""
            }"
            else DevRushTheme.strings.profileLibrary,
            style = DevRushTheme.typography.sfPro14,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        Text(
            if (formattedEndDate.value != null) "${formattedStartDate.value} - ${formattedEndDate.value}"
            else DevRushTheme.strings.accessesPaymentsUnlimitedAccess,
            style = DevRushTheme.typography.sfPro12,
            color = DevRushTheme.colors.c1
        )

        Gap(4)

        if (formattedEndDate.value != null) Text(
            "${DevRushTheme.strings.profileExpires} ${formattedEndDate.value}",
            style = DevRushTheme.typography.sfPro10,
            color = DevRushTheme.colors.c2
        )
    }
}
