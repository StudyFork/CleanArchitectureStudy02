package com.example.movieapplication.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: List<String> = listOf() //ex) {"errors":["page must be greater than 0"]}
)