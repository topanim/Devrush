package io.eduonline.devrush.devrushlmsmultiplatform.domain.repository.profileRepository

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.database.AppDatabase
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.AuthModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.getMe.GetMeResponse
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.GamificationModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Leader
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getAchievements.GetAchievementsRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getLeaders.GetLeadersRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSchoolSettings.GetSchoolSettingsRequest


class ProfileRepository(
    private val authApi: AuthModule,
    private val gamificationApi: GamificationModule,
    private val studentApi: StudentModule,
    private val database: AppDatabase,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend fun getAchievementsFromServer(): List<Achievement> {
        return gamificationApi.getAchievements(
            GetAchievementsRequest(tokenRepository.getTokens().accessToken)
        ).checkedRefresh(tokenRepository).checkedResult().items
    }

    suspend fun getAchievementsFromDatabase(): List<Achievement> {
        return database.getProfileDao().getAchievement().map { it.toAchievement() }
    }

    suspend fun saveAchievements(achievements: List<Achievement>) {
        database.getProfileDao().insertAchievement(achievements.map { it.toAchievementDBO() })
    }

    suspend fun cleanAchievements() {
        database.getProfileDao().cleanAchievement()
    }

    suspend fun saveLeaderBoard(leaderBoard: List<Leader>) {
        database.getProfileDao().insertLeader(leaderBoard.map { it.toLeaderDBO() })
    }

    suspend fun getLeaderBoardFromDatabase(): List<Leader> {
        return database.getProfileDao().getLeader().map { it.toLeader() }
    }

    suspend fun getLeaderBoardFromServer(): List<Leader> {
        val token = tokenRepository.getTokens().accessToken
        val schoolSettings = studentApi.getSchoolSettings(GetSchoolSettingsRequest(token))
            .checkedRefresh(tokenRepository).checkedResult()
        return gamificationApi.getLeaders(
            GetLeadersRequest(token, take = schoolSettings.gamification.leaderboardTakeCount)
        ).checkedRefresh(tokenRepository).checkedResult().items
    }

    suspend fun cleanLeader() {
        database.getProfileDao().cleanLeader()
    }

    suspend fun getProfileFromServer(): GetMeResponse {
        //Не список потому что это пользователь под которым мы зашли
        return authApi.getMe(
            GetMeRequest(tokenRepository.getTokens().accessToken)
        ).checkedRefresh(tokenRepository).checkedResult()
    }

    suspend fun gelAllFromDatabase(): GetMeResponse? {
        val cashedProfiles = database.getProfileDao().getProfiles()
        return cashedProfiles?.toProfileData()
    }

    suspend fun saveProfile(profile: GetMeResponse) {
        val profilesDBO = profile.toProfileDBO()
        database.getProfileDao().insert(profilesDBO)
    }

    suspend fun cleanProfiles() {
        database.getProfileDao().clean()
    }
}
