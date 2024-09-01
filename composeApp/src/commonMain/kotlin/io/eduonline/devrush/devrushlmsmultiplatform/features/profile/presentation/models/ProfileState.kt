package io.eduonline.devrush.devrushlmsmultiplatform.features.profile.presentation.models

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain.SubscriptionsDataItem

data class ProfileState(
    val profileLoadingState: ProfileLoadingState = ProfileLoadingState.Loading,
    val profile: GetMeResponse? = null,
    val fields: ProfileFields? = null,
    val achievementsActivity: List<Achievement>? = emptyList(),
    val achievementsNoActivity: List<Achievement>? = emptyList(),
    val leaderList: List<Leader>? = emptyList(),
    val subscriptionsState: List<SubscriptionsDataItem> = emptyList(),
    val isRefresh: Boolean = false,
)

sealed interface ProfileLoadingState {
    data object Init : ProfileLoadingState
    data object Loading : ProfileLoadingState
    data object Success : ProfileLoadingState
    data class Error(val message: String) : ProfileLoadingState
}


data class ProfileFields(
    private val profile: GetMeResponse,
    var darkTheme: Boolean,
    var name: String = profile.firstName,
    var surname: String? = profile.lastName,
    var imageCloudKey: String? = profile.avatar?.cloudKey,
)




