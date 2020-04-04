package com.template.nanamare.vm

import androidx.lifecycle.MutableLiveData
import com.template.nanamare.base.ui.BaseViewModel

class VideoViewModel(private val liveVideoUrl: String) : BaseViewModel() {
    var liveVideoPath = MutableLiveData<String>().apply {
        value = liveVideoUrl
    }
}