package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.eduonline.devrush.devrushlmsmultiplatform.components.utils.Gap
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models.ProfileState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.ProfileAccessesAndPayments
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.ProfileAchievement
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.ProfileImage
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.ProfileLeaderBoard
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.ui.components.ProfileStatistics

@Composable
fun ProfileView(state: ProfileState, onEvent: (ProfileEvent) -> Unit) {
    val currentUser = state.leaderList?.find {
        it.student.firstName == state.profile?.firstName &&
                it.student.lastName == state.profile?.lastName &&
                it.student.avatar?.cloudKey == state.profile?.avatar?.cloudKey
    }
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Gap(20)
        ProfileImage(state)
        if (currentUser?.place != null) {
            Gap(24)
            ProfileStatistics(state)
        }
        Gap(24)
        if (state.achievementsActivity?.size != null) {
            ProfileAchievement(state, onEvent)
            Gap(24)
        }
        if (state.leaderList != null) {
            ProfileLeaderBoard(state, onEvent)
            Gap(24)
        }
        if (state.subscriptionsState.isNotEmpty()) {
            ProfileAccessesAndPayments(state, onEvent)
        }
    }
}