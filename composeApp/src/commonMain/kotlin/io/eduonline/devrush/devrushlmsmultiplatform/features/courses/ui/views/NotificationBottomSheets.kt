package io.eduonline.devrush.devrushlmsmultiplatform.features.courses.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import devrush_multiplatform.composeapp.generated.resources.Res
import devrush_multiplatform.composeapp.generated.resources.letter_icon
import devrush_multiplatform.composeapp.generated.resources.open_letter_icon
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.notification.models.getNotifications.Notification
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme
import io.eduonline.devrush.devrushlmsmultiplatform.theme.DevRushTheme.strings
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.vectorResource
import kotlin.time.DurationUnit

data class NotificationState(
    val id: String,
    val title: String,
    val isSeen: Boolean,
    val daysAgo: String,
    val amount: Float?,
    val courseTaskAnswerStatus: CourseTaskAnswerStatus?,
    val customNotification: CustomNotification?,
)

data class CourseTaskAnswerStatus(
    val courseName: String?,
    val courseItemName: String?,
    val status: String?,
)

data class CustomNotification(
    val scenarioName: String,
    val message: String,
    val user: User?,
)

data class User(
    val id: String?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
)

@Composable
fun NotificationItem(state: NotificationState) {
    val title = when (state.title) {
        "studentPartnershipRegistration" -> strings.notificationStudentPartnershipRegistration
        "studentPartnershipTransaction" -> strings.notificationStudentPartnershipTransaction
        "passwordChanged" -> strings.notificationPasswordChanged
        "studentBonusTransaction" -> strings.notificationStudentBonusTransaction
        "libraryItemComment" -> strings.notificationLibraryItemComment
        "studentBonus" -> strings.notificationStudentBonus
        "deposit" -> strings.notificationDeposit
        "withdrawal" -> strings.notificationWithdrawal
        "adminPartnershipTransactionRequest" -> strings.notificationAdminPartnershipTransactionRequest
        "courseTaskAnswerStatus" -> when (state.courseTaskAnswerStatus?.status) {
            "correct" -> strings.notificationTaskInStep +
                    " \"${state.courseTaskAnswerStatus.courseItemName}\"" +
                    " ${strings.notificationApproved}" +
                    " ${state.courseTaskAnswerStatus.courseName}"
            "incomplete" -> strings.notificationTaskInStep +
                    " \"${state.courseTaskAnswerStatus.courseItemName}\" " +
                    "${strings.notificationNeedAdd} ${state.courseTaskAnswerStatus.courseName}"
            else -> strings.notificationCourseTaskAnswerStatus
        }
        "customNotification" -> state.customNotification?.message ?: state.title
        else -> state.title
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(DevRushTheme.colors.c6),
        border = BorderStroke(1.dp, DevRushTheme.colors.c5),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            if (state.isSeen) DevRushTheme.colors.c3
                            else DevRushTheme.colors.blue1
                        )
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (state.isSeen) vectorResource(resource = Res.drawable.open_letter_icon)
                        else vectorResource(resource = Res.drawable.letter_icon),
                        contentDescription = null,
                        tint = DevRushTheme.colors.background
                    )
                }
                Gap(size = 10)

                Column {
                    Text(
                        text = title,
                        style = DevRushTheme.typography.sfProBold14,
                        color = DevRushTheme.colors.c1
                    )
                    Gap(size = 6)
                    Text(
                        text = state.daysAgo,
                        style = DevRushTheme.typography.sfPro12,
                        color = DevRushTheme.colors.c2
                    )
                }
            }

            if (state.amount != null) {
                Gap(size = 16)
                Row {
                    Text(
                        modifier = Modifier
                            .width(93.dp)
                            .height(17.dp),
                        text = "Сумма",
                        style = DevRushTheme.typography.sfProBold14,
                        color = DevRushTheme.colors.c1
                    )
                    Text(
                        text = "${state.amount} руб.",
                        style = DevRushTheme.typography.sfPro14,
                        color = DevRushTheme.colors.c1
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationBottomSheets(
    notifications: List<Notification>,
    onDismiss: () -> Unit,
    eventHandler: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
        shape = BottomSheetDefaults.HiddenShape,
        containerColor = DevRushTheme.colors.background,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp).background(DevRushTheme.colors.background)
        ) {
            item {
                Text(
                    text = strings.listOfCoursesNotifications,
                    style = DevRushTheme.typography.sfProBold16,
                    color = DevRushTheme.colors.c1
                )

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    modifier = Modifier.clickable {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                eventHandler()
                            }
                        }
                    },
                    text = strings.listOfCoursesMarkAll,
                    style = DevRushTheme.typography.sfProBold14,
                    color = DevRushTheme.colors.blue1,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.size(16.dp))
            }

            items(notifications) { notification ->
                val timeDifference by remember {
                    mutableStateOf(
                        calculateTimeDifference(
                            notification.createdDate
                        )
                    )
                }

                NotificationItem(
                    NotificationState(
                        id = notification.id,
                        title = notification.type,
                        isSeen = notification.seenDate != null,
                        daysAgo = timeDifference,
                        amount = notification.payload.amount,
                        courseTaskAnswerStatus = CourseTaskAnswerStatus(
                            courseName = notification.payload.courseName,
                            courseItemName = notification.payload.courseItemName,
                            status = notification.payload.status,
                        ),
                        customNotification = CustomNotification(
                            scenarioName = notification.payload.scenarioName ?: "",
                            message = notification.payload.message ?: "",
                            user = User(
                                id = notification.payload.user?.id,
                                firstName = notification.payload.user?.firstName,
                                lastName = notification.payload.user?.lastName,
                                email = notification.payload.user?.email,
                                phone = notification.payload.user?.phone
                            )
                        )
                    )
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

fun calculateTimeDifference(dateString: String): String {

    val parsedDate = Instant.parse(dateString).toLocalDateTime(TimeZone.UTC)
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.UTC)

    val duration = currentDate.toInstant(TimeZone.UTC) - parsedDate.toInstant(TimeZone.UTC)

    val hours = duration.toDouble(DurationUnit.HOURS).toLong()
    val days = duration.toDouble(DurationUnit.DAYS).toLong()
    val months = days / 30

    val hName = when (hours) {
        1L -> "час"
        in 2L..4L -> "часа"
        21L -> "час"
        in 22L..24L -> "часа"
        else -> "часов"
    }
    val dName = when (days) {
        1L -> "день"
        in 2L..4L -> "дня"
        21L -> "день"
        in 22L..24L -> "дня"
        else -> "дней"
    }
    val mName = when (months) {
        1L -> "месяц"
        in 2L..4L -> "месяца"
        21L -> "месяц"
        in 22L..24L -> "месяца"
        else -> "месяцев"
    }

    return when {
        months > 0 -> "$months $mName назад"
        days > 0 -> "$days $dName назад"
        hours > 0 -> "$hours $hName назад"
        else -> "только что"
    }
}