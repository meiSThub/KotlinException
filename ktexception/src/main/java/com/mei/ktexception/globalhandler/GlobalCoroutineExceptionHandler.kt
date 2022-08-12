package com.mei.ktexception.globalhandler

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
class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        println("GlobalCoroutineExceptionHandler：${context}")
        exception.printStackTrace()
    }
}