package com.template.nanamare.network.response

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BaseErrorDeserializer : JsonDeserializer<BaseErrorResponse> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): BaseErrorResponse {

        val jsonObject = json?.asJsonObject ?: throw NullPointerException()

        val statusCode = jsonObject.get("status_code").asInt
        val statusMessage = jsonObject.get("status_message").asString
        val success = jsonObject.get("success").asBoolean

        return BaseErrorResponse(statusCode, statusMessage, success)
    }

}