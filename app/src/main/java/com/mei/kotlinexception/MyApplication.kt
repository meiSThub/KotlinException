package com.mei.kotlinexception

import android.app.Application
import android.util.Log

/**
 * @date 2022/8/12
 * @author mxb
 * @desc
 * @desired
 */
class MyApplication : Application() {
    companion object {
        private const val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.w(TAG, "onCreate: thread=$thread,exception=$throwable")
        }
    }
}