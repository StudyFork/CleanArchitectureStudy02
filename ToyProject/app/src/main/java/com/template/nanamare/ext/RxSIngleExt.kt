package com.template.nanamare.ext

import com.template.nanamare.network.response.BaseResponse
import io.reactivex.Single
import retrofit2.Response

@Suppress("UNCHECKED_CAST")
fun <T> Single<in T>.upCasting(): Single<Response<BaseResponse>>  {
    return this as Single<Response<BaseResponse>>
}