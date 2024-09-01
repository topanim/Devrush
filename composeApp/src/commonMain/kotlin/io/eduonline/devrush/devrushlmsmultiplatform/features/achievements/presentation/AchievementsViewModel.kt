package io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation

import androidx.lifecycle.viewModelScope
import io.eduonline.devrush.devrushlmsmultiplatform.base.BaseViewModel
import io.eduonline.devrush.devrushlmsmultiplatform.base.ExpiredTokenException
import io.eduonline.devrush.devrushlmsmultiplatform.base.safeExecute
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchieveLoadingState
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsAction
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsEvent
import io.eduonline.devrush.devrushlmsmultiplatform.features.achievements.presentation.models.AchievementsViewState
import io.eduonline.devrush.devrushlmsmultiplatform.features.profile.domain.achievements.GetCachedAchievementsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class AchievementsViewModel(
    private val getCachedAchievementsUseCase: GetCachedAchievementsUseCase,
) : BaseViewModel<AchievementsViewState, AchievementsAction, AchievementsEvent>(
    AchievementsViewState()
), KoinComponent {
    override fun obtainEvent(viewEvent: AchievementsEvent) {
        when (viewEvent) {
            AchievementsEvent.OnBackPress -> viewAction = AchievementsAction.PresencePast
            is AchievementsEvent.GetCourse -> loadAchievements(viewEvent.retried)
            is AchievementsEvent.Refresh -> {
                loadAchievements(true)
                viewState = viewState.copy(isRefresh = !viewState.isRefresh)
            }

            is AchievementsEvent.OpenAchievement -> viewState =
                viewState.copy(openAchievement = !viewState.openAchievement)

            is AchievementsEvent.SetAchievement -> {
                viewEvent.achievement?.let { achievement(it) }

            }
        }
    }

    private fun loadAchievements(retried: Boolean) {
        safeExecute(
            scope = viewModelScope,
            block = {
                val cachedAchievements = getCachedAchievementsUseCase()
                withContext(Dispatchers.Main) {
                    updateUI(
                        achievements = cachedAchievements
                    )
                }

                if (viewState.isRefresh) {
                    AchieveLoadingState.Loading
                    val achievements = getCachedAchievementsUseCase()
                    withContext(Dispatchers.Main) {
                        updateUI(
                            achievements = achievements
                        )
                    }
                }

                refreshing(false)
                delay(100)
            }
        ) {
            refreshing(false)
            if (it is ExpiredTokenException && !retried) {
                loadAchievements(true)
            } else viewState = viewState.copy(
                fetchCourseInfoRequest = AchieveLoadingState.Error(it.message ?: "")
            )
        }

    }

    private fun refreshing(value: Boolean) {
        viewState = viewState.copy(isRefresh = value)

    }

    private fun updateUI(
        achievements: List<Achievement>,
    ) {
        viewState = viewState.copy(
            fetchCourseInfoRequest = AchieveLoadingState.Success,
            achievementsActivity = achievements.filter { it.count != 0 },
            achievementsNoActivity = achievements.filter { it.count == 0 }
        )
    }

    private fun achievement(value: Achievement) {
        viewState = viewState.copy(currentAchievement = value)

    }
}