package com.template.nanamare.network.response


import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("genres")
    val genres: List<Genre>
) : BaseResponse {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}