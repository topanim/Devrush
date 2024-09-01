package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses.GetLicensesRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getLicenses.License
import io.eduonline.devrush.devrushlmsmultiplatform.utils.parseStringToLocalDateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class AccessesData(
    val active: List<AccessDataItem>,
    val inactive: List<AccessDataItem>,
)

sealed interface AccessesDataItemType {
    data object Course : AccessesDataItemType
    data object Library : AccessesDataItemType
}

data class AccessDataItem(
    val id: String,
    val name: String,
    val imageCloudKey: String?,
    val type: AccessesDataItemType,
    val rate: String?,
    val startDate: String,
    val endDate: String?,
    val expired: Boolean,
)

class GetAccessesDataUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(expired: Boolean, useFilter: Boolean = true): List<AccessDataItem> {
        return api.getLicenses(
            GetLicensesRequest(
                tokenRepository.getTokens().accessToken,
                false,
                take = 50
            )
        ).checkedRefresh(tokenRepository).checkedResult().items
            .filter {
                if (!useFilter) return@filter true
                if (it.endDate != null) {
                    val endDate = parseStringToLocalDateTime(it.endDate)
                    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
                    val subscriptionExpired = endDate < today
                    subscriptionExpired == expired
                } else !expired
            }.map { it.toAccessDataItem(expired) }
    }

    private fun License.toAccessDataItem(expired: Boolean): AccessDataItem {
        val type = when (this.type) {
            "course" -> AccessesDataItemType.Course
            "library" -> AccessesDataItemType.Library
            else -> throw Exception("Unknown type")
        }

        val id: String?
        val imageCloudKey: String?
        val title: String
        val rate: String?

        when (type) {
            AccessesDataItemType.Course -> {
                imageCloudKey = this.course!!.primaryImage?.file?.cloudKey
                title = this.course.name
                id = this.course.id
                rate = this.coursePlan?.name
            }

            AccessesDataItemType.Library -> {
                imageCloudKey = this.libraryAccess!!.library.primaryImage?.file?.cloudKey
                title = this.libraryAccess.library.name
                id = this.libraryAccess.library.id
                rate = null
            }
        }

        return AccessDataItem(
            id = id,
            name = title,
            imageCloudKey = imageCloudKey,
            type = type,
            rate = rate,
            startDate = this.beginDate,
            endDate = this.endDate,
            expired = expired
        )
    }
}