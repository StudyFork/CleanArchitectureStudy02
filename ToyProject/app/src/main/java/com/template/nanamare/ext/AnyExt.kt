package com.template.nanamare.ext

import com.google.gson.Gson

inline fun Any.toJsonString() = Gson().toJson(this)