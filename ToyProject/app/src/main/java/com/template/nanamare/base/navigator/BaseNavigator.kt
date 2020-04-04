package com.template.nanamare.base.navigator

import android.os.Bundle

interface BaseNavigator {

    fun showLoadingPopup()

    fun hideLoadingPopup()

    fun networkError(errorCode: String = "")

    fun showToast(resId: Int, error: Boolean = false)

    fun showToast(msg: String, error: Boolean = false)

    fun errorHandling(errorCode: String = "")

    fun logFirebaseEvent(name: String, params: Bundle? = Bundle())

    fun hideKeyboard()

}