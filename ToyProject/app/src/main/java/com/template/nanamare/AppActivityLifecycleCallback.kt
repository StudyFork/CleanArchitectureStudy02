package com.template.nanamare

import android.app.Activity
import android.app.Application
import android.os.Bundle

object AppActivityLifecycleCallback
    : Application.ActivityLifecycleCallbacks {

    var activityCnt = 0
        private set

    override fun onActivityPaused(activity: Activity?) {
        activityCnt -= 1
    }

    override fun onActivityResumed(activity: Activity?) {
        activityCnt += 1
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    fun isActivityVisible() = activityCnt != 0
}