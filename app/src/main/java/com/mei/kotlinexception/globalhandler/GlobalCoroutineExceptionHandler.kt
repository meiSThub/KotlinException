package com.mei.kotlinexception.globalhandler

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.Keep
import com.google.auto.service.AutoService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @date 2022/8/9
 * @author mxb
 * @desc   全局异常处理
 * @desired
 */
// @AutoService(CoroutineExceptionHandler::class)
@Keep
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    @SuppressLint("LongLogTag")
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        Log.w("GlobalCoroutineExceptionHandler", "context=${context} ")
        exception.printStackTrace()
    }
}