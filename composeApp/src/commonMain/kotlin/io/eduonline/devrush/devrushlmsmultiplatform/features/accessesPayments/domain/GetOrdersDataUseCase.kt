package io.eduonline.devrush.devrushlmsmultiplatform.features.accessesPayments.domain

import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedRefresh
import io.eduonline.devrush.devrushlmsmultiplatform.base.checkedResult
import io.eduonline.devrush.devrushlmsmultiplatform.domain.auth.AuthTokenRepository
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.StudentModule
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders.GetOrdersRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders.OrderItem

data class OrderData(
    val items: List<OrderDataItem>,
)

data class OrderDataItem(
    val id: String,
    val number: Int,
    val createdDate: String,
    val currency: String,
    val completed: Boolean,
    val fullyPaid: Boolean,
    val paidDate: String?, // TODO: null
    val paidAmount: Float,
    val totalAmount: Float,
)

class GetOrdersDataUseCase(
    private val api: StudentModule,
    private val tokenRepository: AuthTokenRepository,
) {
    suspend operator fun invoke(): List<OrderDataItem> {
        return api.getOrders(
            GetOrdersRequest(
                tokenRepository.getTokens().accessToken
            )
        ).checkedRefresh(tokenRepository).checkedResult().items
            .map { it.toOrderDataItem() }
    }

    private fun OrderItem.toOrderDataItem() = OrderDataItem(
        completed = completed,
        number = number,
        createdDate = createdDate,
        currency = currency,
        fullyPaid = fullyPaid,
        id = id,
        paidDate = paidDate,
        paidAmount = paidAmount,
        totalAmount = totalAmount
    )

}