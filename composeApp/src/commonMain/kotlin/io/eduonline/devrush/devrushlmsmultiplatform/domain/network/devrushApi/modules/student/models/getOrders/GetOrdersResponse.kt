package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders

import kotlinx.serialization.Serializable

@Serializable
data class GetOrdersResponse(
    val items: List<OrderItem>,
)

@Serializable
data class OrderItem(
    val id: String,
    val createdDate: String,
    val paidDate: String?,
    val currency: String,
    val isDigitalDelivery: Boolean,
    val fullyPaid: Boolean,
//    val shippingPrice: Any?,
    val totalAmount: Float,
    val paidAmount: Float,
    val savingsAmount: Float,
    val status: String,
    val completed: Boolean,
    val number: Int,
    val contents: List<ContentsItem>,
)

@Serializable
data class ContentsItem(
    val id: String,
    val quantity: Int,
    val product: ProductItem,
)

@Serializable
data class ProductItem(
    val id: String,
    val name: String,
    val type: String,
    val price: Float,
    val softDeleted: Boolean,
)