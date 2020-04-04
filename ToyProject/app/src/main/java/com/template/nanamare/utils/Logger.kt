package com.template.nanamare.utils


import android.util.Log
import com.template.nanamare.BuildConfig

/**
 * tag : 호출 클래스 이름
 * message : 디버그 원하는 값
 */
object Logger {

    private const val BUILD_TYPE = "debug"
    private const val FLAVOR = "dev"

    private const val FORMAT : String = "[%s] : %s"

    /** Log Level Error  */
    fun e(tag: String, exception: Exception, content : String = "") {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE || BuildConfig.FLAVOR == FLAVOR) {
            Log.e(tag, String.format(FORMAT, getCallerInfo(), content + ' ' + Log.getStackTraceString(exception)))
        }
    }

    /** Log Level Warning  */
    fun w(tag: String, message: String) {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE || BuildConfig.FLAVOR == FLAVOR)
            Log.w(tag, String.format(FORMAT, getCallerInfo(), message))
    }

    /** Log Level Information  */
    fun i(tag: String, message: String) {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE || BuildConfig.FLAVOR == FLAVOR)
            Log.i(tag, String.format(FORMAT, getCallerInfo(), message))
    }

    /** Log Level Debug  */
    fun d(tag: String, message: String) {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE || BuildConfig.FLAVOR == FLAVOR)
            Log.d(tag, String.format(FORMAT, getCallerInfo(), message))
    }

    /** Log Level Verbose  */
    fun v(tag: String, message: String) {
        if (BuildConfig.BUILD_TYPE == BUILD_TYPE || BuildConfig.FLAVOR == FLAVOR)
            Log.v(tag, String.format(FORMAT, getCallerInfo(), message))
    }

    private fun getCallerInfo(): String {
        val elements = Exception().stackTrace
        val className = elements[2].className
        return className.substring(className.lastIndexOf(".") + 1, className.length) + "_" + elements[2].lineNumber
    }

    private fun getStackTrace() : String {
         var stackTrace = Thread.currentThread().stackTrace[4]
        return stackTrace.methodName
    }



}
