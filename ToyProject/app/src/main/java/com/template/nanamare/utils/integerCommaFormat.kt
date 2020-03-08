package com.template.nanamare.utils

import android.content.Context
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

val integerCommaFormat = DecimalFormat("#,###")
val sdfDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
val sdfDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

class UiUtils(private val stringContext: Context,
              private val stringResUtils: StringResUtils
) {

}
