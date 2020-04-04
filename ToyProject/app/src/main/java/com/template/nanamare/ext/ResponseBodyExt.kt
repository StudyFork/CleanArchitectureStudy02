package com.template.nanamare.ext

import com.google.gson.GsonBuilder
import com.template.nanamare.network.response.BaseErrorDeserializer
import com.template.nanamare.network.response.BaseErrorResponse

fun converterErrorBody(json: String) : BaseErrorResponse {
    return GsonBuilder().registerTypeAdapter(
        BaseErrorResponse::class.java,
        BaseErrorDeserializer()
    ).create().fromJson(json, BaseErrorResponse::class.java)

}