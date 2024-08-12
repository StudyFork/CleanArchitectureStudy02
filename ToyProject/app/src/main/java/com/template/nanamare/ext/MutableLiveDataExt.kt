package com.template.nanamare.ext

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.network.NetworkState
import com.template.nanamare.network.response.BaseResponse

@Suppress("UNCHECKED_CAST")
fun <T> MutableLiveData<in T>.upCasting(): MutableLiveData<NetworkState<BaseResponse>> {
    return this as MutableLiveData<NetworkState<BaseResponse>>
}