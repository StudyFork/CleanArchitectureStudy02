package com.template.nanamare.base.ui

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process
import com.template.nanamare.base.ui.BaseDefaultErrorActivity.Companion.EXTRA_ERROR_TEXT
import com.template.nanamare.base.ui.BaseDefaultErrorActivity.Companion.EXTRA_INTENT
import com.template.nanamare.utils.Logger
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

class BaseExceptionHandler(
        application: Application,
        private val defaultExceptionHandler: Thread.UncaughtExceptionHandler,
        private val fabricExceptionHandler: Thread.UncaughtExceptionHandler
) : Thread.UncaughtExceptionHandler {

    private var lastActivity: Activity? = null
    private var activityCount = 0

    init {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {

                }

                override fun onActivityDestroyed(activity: Activity?) {

                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    lastActivity = activity
                }

                override fun onActivityStarted(activity: Activity) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    activityCount++
                    lastActivity = activity
                }

                override fun onActivityStopped(activity: Activity) {
                    if (isSkipActivity(activity)) {
                        return
                    }
                    activityCount--
                    if (activityCount < 0) {
                        lastActivity = null
                    }

                }
            })
    }

    private fun isSkipActivity(activity: Activity) = activity is BaseDefaultErrorActivity

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        fabricExceptionHandler.uncaughtException(thread, throwable)
        lastActivity?.run {
            val stringWriter = StringWriter()
            throwable.printStackTrace(PrintWriter(stringWriter))

            Logger.d(TAG, stringWriter.toString())

            startErrorActivity(this, stringWriter.toString())
        } ?: defaultExceptionHandler.uncaughtException(thread, throwable)

        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }

    private fun startErrorActivity(activity: Activity, errorText: String) = activity.run {
        val errorActivityIntent = Intent(this, BaseDefaultErrorActivity::class.java)
            .apply {
                putExtra(EXTRA_INTENT, intent)
                putExtra(EXTRA_ERROR_TEXT, errorText)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
        startActivity(errorActivityIntent)
        finish()
    }

    companion object {
        @JvmStatic
        val TAG: String = BaseExceptionHandler::class.java.simpleName
    }

}