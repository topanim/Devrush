package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.getProducts


import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.gamification.models.common.ProductsFilter
import kotlinx.serialization.Serializable

@Serializable
class GetProductsResponse(
    val filter: ProductsFilter,
    val items: List<Product>,
)

@Serializable
data class Product(
    val id: String,
    val title: String,
    val description: String?,
    val points: Int,
    val useAvailableCount: Boolean,
    val availableCount: Int,
    val contentType: String,
    val bonusAmount: Float,
    val image: Image,
    val product: ProductOfProduct?,
    val purchasedDate: String?,
)

@Serializable
data class Image(
    val name: String,
    val cloudKey: String,
    val externalUrl: String?,
    val size: Int,
    val mimeType: String,
    val type: String,
    val extension: String,
    val createdDate: String,
    val isVoice: Boolean,
    val additionalData: String?,
    val id: String,
    val softDeleted: Boolean,
    val softDeletedDate: String?,
)

@Serializable
data class ProductOfProduct(
    val name: String,
    val description: String,
    val type: String,
)