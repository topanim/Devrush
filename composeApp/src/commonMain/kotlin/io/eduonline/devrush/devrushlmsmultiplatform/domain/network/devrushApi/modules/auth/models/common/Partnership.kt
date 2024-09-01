package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.auth.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Partnership(
    val id: String? = null,
    val isActive: Boolean? = null,

    val paymentGatewayFeeEnabled: Boolean? = null,
    val levelCount: Int,

    val canUseReferalBonus: Boolean? = null,
    val canUsePartnerBonus: Boolean? = null,

    val partnershipVisible: Boolean? = null,
    val partnerAssigningCookieDurationDays: Int? = null,
    val partnerAssigningDurationType: String? = null,
    val partnerAssigningDurationDays: Int? = null,
    val partnershipPaidOrdersVisible: Boolean,
    val partnershipAllOrdersVisible: Boolean,
    val partnershipOrdersContactVisible: Boolean,
    val partnershipVisibleToGroupId: PartnershipVisibleToGroupId? = null, //TODO: need to be fixed

    val transferFeeMethod: String? = null,
    val transferBonusPartner: Boolean? = null,
    val transferBonusPartnerAmount: Float? = null,
    val transferBonusReferral: Boolean? = null,
    val transferBonusReferralAmount: Float? = null,
    val transferFeeAllowedToBonus: Boolean,
    val transferFeeAllowed: Boolean,
    val transferFeeMinAmount: Float,
    val transferFeeHoldingPeriodDays: Int,
)