package io.eduonline.devrush.devrushlmsmultiplatform.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Avatar
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.ChatUserData
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Group
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.School
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Session
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common.Tag
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Achievement
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.Student

@Entity(tableName = "profile")
data class ProfileDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("email") val email: String,
    @ColumnInfo("firstName") val firstName: String,
    @ColumnInfo("middleName") val middleName: String?,
    @ColumnInfo("lastName") val lastName: String?,
    @ColumnInfo("phone") val phone: String?,
    @ColumnInfo("lang") val lang: String,
    @ColumnInfo("gender") val gender: String,
    @ColumnInfo("birthday") val birthday: String?,
    @ColumnInfo("bonusBalance") val bonusBalance: Float,
    @ColumnInfo("isEmailConfirmed") val isEmailConfirmed: Boolean,
    @ColumnInfo("isSchoolEmailConfirmed") val isSchoolEmailConfirmed: Boolean,
    @ColumnInfo("emailConfirmationSentDate") val emailConfirmationSentDate: String?,
    @ColumnInfo("isPhoneConfirmed") val isPhoneConfirmed: Boolean,
    @ColumnInfo("phoneConfirmationSentDate") val phoneConfirmationSentDate: String?,
    @ColumnInfo("needChangePassword") val needChangePassword: Boolean,
    @ColumnInfo("isAdmin") val isAdmin: Boolean,
    @ColumnInfo("concurrencyStamp") val concurrencyStamp: String,
    @ColumnInfo("roles") val roles: List<String>, // TODO: need to be fixed
    @ColumnInfo("school") val school: School,
    @ColumnInfo("avatar") val avatar: Avatar?,
    @ColumnInfo("session") val session: Session,
    @ColumnInfo("chatUserData") val chatUserData: ChatUserData,
    @ColumnInfo("tags") val tags: List<Tag>,
    @ColumnInfo("groups") val groups: List<Group>,
)

@Entity(tableName = "leaderboard")
data class LeaderDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("place") val place: Int,
    @ColumnInfo("score") val score: Int,
    @ColumnInfo("student") val student: Student,
    @ColumnInfo("achievements") val achievements: List<Achievement>,
)

@Entity(tableName = "achievements")
data class AchievementDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("title") val title: String? = null,
    @ColumnInfo("description") val description: String? = null,
    @ColumnInfo("systemImage") val systemImage: String? = null, // TODO: need to be fixed
    @ColumnInfo("customCloudKey") val customCloudKey: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("subtype") val subtype: String? = null, // TODO: need to be fixed
    @ColumnInfo("imageType") val imageType: String,
    @ColumnInfo("gamificationStyle") val gamificationStyle: String,
    @ColumnInfo("systemAchievementId") val systemAchievementId: String? = null,
    @ColumnInfo("imageCloudKey") val imageCloudKey: String,
    @ColumnInfo("points") val points: Int,
    @ColumnInfo("count") val count: Int,
    @ColumnInfo("achievedDate") val achievedDate: String? = null,
)