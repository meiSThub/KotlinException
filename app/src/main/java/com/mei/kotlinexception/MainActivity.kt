package com.mei.kotlinexception

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun go2Cancel(view: View) {
        // startActivity(Intent(this, CancelActivity::class.java))
        startActivity(Intent(this, CancelActivity2::class.java))
    }

    fun throwException(view: View) {
        lifecycleScope.launch(Dispatchers.IO) {
            1 / 0
        }
    }

    private fun getWeather(callback: HttpCallBack<String>) {
        // 模拟耗时操作

        if ((Math.random() * 10).toInt() % 2 == 0) {
            callback.onSuccess("天气晴朗")
        } else {
            callback.onFail("404", "链接异常")
        }
    }

    suspend fun getWeather(city: String): String =
        suspendCoroutine { continuation: Continuation<String> ->
            // MyHttp.postJson(url)
            //     .param("city", city)
            //     .execute(object : HttpCallBack<String> {// 异步执行
            //     override fun onSuccess(data: String) {
            //         // 通过 Continuation 对象，返回请求的接口
            //         continuation.resume(data)
            //     }
            //
            //         override fun onFail(errorCode: String?, errorMsg: String?) {
            //             // 通过Continuation对象，抛出异常
            //             continuation.resumeWithException(ServerException(errorCode, errorMsg))
            //         }
            //     })
        }
}

class ServerException(var errCode: String? = null, message: String?) : Exception(message)

interface HttpCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(errorCode: String?, errorMsg: String?)
}