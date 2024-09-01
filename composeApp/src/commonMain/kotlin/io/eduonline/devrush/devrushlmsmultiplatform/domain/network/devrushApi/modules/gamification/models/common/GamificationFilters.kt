package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common

import kotlinx.serialization.Serializable

interface GamificationFilter {
    val skip: Int
    val take: Int
    val itemsTotal: Int
    val useBaseFilter: Boolean
}

@Serializable
data class AchievementsFilter(
    override val skip: Int,
    override val take: Int,
    override val itemsTotal: Int,
    override val useBaseFilter: Boolean,
) : GamificationFilter

@Serializable
data class LeadersFilter(
    override val skip: Int,
    override val take: Int,
    override val itemsTotal: Int,
    override val useBaseFilter: Boolean,
) : GamificationFilter


@Serializable
data class ProductsFilter(
    override val skip: Int,
    override val take: Int,
    override val itemsTotal: Int,
    override val useBaseFilter: Boolean,

    val id: String?,
    val enabled: Boolean,
    val contentType: String?,
    val productId: String?,
    val gamificationId: String?,
    val search: String?,
    val sortName: String,
    val sortType: String,
    val useSort: Boolean,
    val useItemsTotal: Boolean,
    val softDeleted: Boolean,
    val ids: List<String>?,
) : GamificationFilter