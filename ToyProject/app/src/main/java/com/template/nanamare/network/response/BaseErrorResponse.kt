package com.template.nanamare.network.response

data class BaseErrorResponse(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)