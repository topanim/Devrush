package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.chat.models.getMessages

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest
import kotlinx.serialization.Serializable

@Serializable
data class GetMessagesRequest(
    override val skip: Int = 0,
    override val take: Int = 25,
    val conversationId: String = "",

    val softDeleted: Boolean? = null,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = true,
    val useItemsTotal: Boolean = true,
) : PaginationRequest