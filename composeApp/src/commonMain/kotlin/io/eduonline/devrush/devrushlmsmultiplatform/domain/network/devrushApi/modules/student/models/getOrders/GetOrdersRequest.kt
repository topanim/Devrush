package io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.modules.student.models.getOrders

import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.ApiAuthRequest
import io.eduonline.devrush.devrushlmsmultiplatform.domain.network.devrushApi.PaginationRequest

class GetOrdersRequest(
    override val token: String,

    override val skip: Int = 0,
    override val take: Int = 25,
    val softDeleted: Boolean = false,
    val useSort: Boolean = true,
    val useBaseFilter: Boolean = true,
    val useItemsTotal: Boolean = true,

    val fields: String = "{id,createdDate,paidDate,currency,isDigitalDelivery,fullyPaid,shippingPrice,totalAmount,paidAmount,savingsAmount,status,completed,number,contents{id,quantity,product{id,name,type,price,softDeleted}}}",
) : ApiAuthRequest, PaginationRequest