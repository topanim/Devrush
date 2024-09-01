package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getSubscriptions

import kotlinx.serialization.Serializable

@Serializable
data class GetSubscriptionsResponse(
    val items: List<Subscription>,
)

@Serializable
data class Subscription(
    val id: String,
    val beginDate: String,
    val endDate: String,
    val creditEndDate: String,
    val type: String,
    val state: String,
    val product: Product,
)

@Serializable
data class Product(
    val id: String,
    val name: String,
    val subsPrice: Float,
    val subsDuration: String,
    val subsDurationType: String,
    val image: Image,
)

@Serializable
data class Image(
    val id: String,
    val name: String,
    val cloudKey: String,
    val extension: String,
    val size: String,
    val type: String,
)